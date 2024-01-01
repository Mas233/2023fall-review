import numpy as np
import PIL.Image as pilImage
import random

'''
    图像读取和预处理
    :param path 图像路径
    :return imgData 预处理完成的图像数据
'''
def imgPreProcess(path):
    # 读入图像
    file=open(path,"rb")
    imgData=[]
    tmpImg=pilImage.open(file)
    # 获取图像长度、宽度
    imgLength,imgWidth=tmpImg.size
    # 依据像素点读取图像rgb值，填入imgData
    for i in range(0,imgLength,1):
        for j in range(0,imgWidth,1):
            imgPixel=[]
            r,g,b=tmpImg.getpixel((i,j))
            imgPixel.append(r/256.0)
            imgPixel.append(g/256.0)
            imgPixel.append(b/256.0)
            imgData.append(imgPixel)
    file.close()
    # 将imgData以numpy矩阵形式返回
    imgData=np.mat(imgData)
    return imgData

'''
    计算两个向量间的欧氏距离（平方）
    :param vector1 向量1
    :param vector2 向量2
    :return ans 计算结果
'''
def calculateDistance(vector1, vector2):
    ans = (vector1 - vector2) * (vector1 - vector2).T
    return ans[0, 0]

'''
    KMeans算法实现
    :param data 图像预处理后存储的矩阵数据
    :param k 分类中心数
    :return [centerPoints,pointsCluster] 聚类中心，样本点聚类情况
'''
def myKmeans(data,k):
    # 随机选取初始的k个中心
    pointsNum,attributeNum=np.shape(data)
    centerPoints=np.mat(np.zeros((k,attributeNum)))
    randomList=random.sample(range(0,pointsNum-1),k)
    for i in range(0,k,1):
        for j in range(0,attributeNum,1):
            centerPoints[i,j]=data[randomList[i],j]
    # 迭代执行聚类工作
    print("start cluster loop...")
    pointsCluster=np.mat(np.zeros((pointsNum,2)))
    loopCounter=0
    loopFlag=True
    # 迭代停止条件：达到稳态无修改或迭代超过10次
    while loopFlag==True and loopCounter<10:
        loopCounter+=1
        loopFlag=False
        # 计算每个样本点到各聚类中心的距离，确定与之最近的聚类中心,并划入该聚类中
        changeCounter=0.0
        for i in range(0,pointsNum,1):
            # 确定最近邻聚类中心
            minDistance = np.inf
            minCenterIndex = -1
            for j in range(0,k,1):
                distance=calculateDistance(data[i,],centerPoints[j,])
                if distance < minDistance:
                    minDistance = distance
                    minCenterIndex = j
            # 划入该聚类中
            if pointsCluster[i, 0] != minCenterIndex:
                changeCounter+=1.0
                pointsCluster[i,]=np.mat([minCenterIndex,minDistance])
        # 针对各聚类，重新计算聚类中心
        for i in range(0,k,1):
            clusterSum=np.mat(np.zeros((1,attributeNum)))
            clusterPointsNum=0
            for j in range(0,pointsNum,1):
                if pointsCluster[j,0]==i:
                    clusterSum+=data[j,]
                    clusterPointsNum+=1
            for j in range(0,attributeNum,1):
                if clusterPointsNum!=0:
                    centerPoints[i,j]=clusterSum[0,j]/clusterPointsNum
        if changeCounter/pointsNum>0.02:
            loopFlag=True
        print("loop",loopCounter,"finished")
    print("cluster loop all finished!")
    return [centerPoints,pointsCluster]

if __name__=='__main__':
    # 图片选自: 麦田里的丝柏树，梵高，1889
    # Wheat Field with Cypresses,Vincent Willem van Gogh,1889
    pre_data=imgPreProcess("picture.jpg")
    for k in range(1,7,1):
        centerPoints,pointsCluster=myKmeans(pre_data,k)
        # 将聚类后的图片可视化保存
        resultPath="result-"+str(k)+".jpg"
        file=open("picture.jpg","rb")
        tmpImg=pilImage.open(file)
        imgLength,imgWidth=tmpImg.size
        file.close()
        newPicture = pilImage.new("RGB", (imgLength,imgWidth))
        num= np.shape(pointsCluster)[0]
        for i in range(num):
            clusterIndex = int(pointsCluster[i, 0])
            r = int(float(centerPoints[clusterIndex, 0]) * 256)
            g = int(float(centerPoints[clusterIndex, 1]) * 256)
            b = int(float(centerPoints[clusterIndex, 2]) * 256)
            newPicture.putpixel((int(i / imgWidth), (i % imgWidth)), (r, g, b))
        newPicture.save(resultPath,"JPEG")