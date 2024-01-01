import torch
import torch.nn as my_nn
import torchvision.datasets as my_datasets
import torchvision.transforms as my_transforms
import torch.utils.data as my_data_utils

# 使用pytorch提供的Mnist数据集
# 获取训练集，并将原数据规范到(0,1)区间
train_dataset = my_datasets.MNIST(root='./dataset', train=True, transform=my_transforms.ToTensor(), download=True)
train_data_loader = my_data_utils.DataLoader(dataset=train_dataset, batch_size=64, shuffle=True)
# 获取测试集，并将原数据规范到(0,1)区间
test_dataset = my_datasets.MNIST(root='./dataset', train=False, transform=my_transforms.ToTensor(), download=True)
test_data_loader = my_data_utils.DataLoader(dataset=test_dataset, batch_size=64, shuffle=True)

'''
    CNN类，构造的卷积神经网络
'''
class CNN(my_nn.Module):

    '''
        构造函数，构造出一个具有五层的卷积神经网络
        :param self
    '''
    def __init__(self):
        super(CNN, self).__init__()
        # 输入层为1x28x28
        # 第一层为卷积层，通过后应为25X26X26
        self.layer1 = my_nn.Sequential(
            my_nn.Conv2d(1, 25, kernel_size=3),
            my_nn.BatchNorm2d(25),
            # 使用RelU激活函数，正值不变，非正值返回0
            my_nn.ReLU(inplace=True)
        )
        # 第二层为采样层做最大池化，通过后为25X13X13
        self.layer2 = my_nn.Sequential(
            my_nn.MaxPool2d(kernel_size=2, stride=2)
        )
        # 第三层为卷积层，通过后为50X11X11
        self.layer3 = my_nn.Sequential(
            my_nn.Conv2d(25, 50, kernel_size=3),
            my_nn.BatchNorm2d(50),
            # 使用RelU激活函数，正值不变，非正值返回0
            my_nn.ReLU(inplace=True)
        )
        # 第四层为采样层做最大池化，通过后为50X5X5
        self.layer4 = my_nn.Sequential(
            my_nn.MaxPool2d(kernel_size=2, stride=2)
        )
        # 第五层为全连接层
        self.layer5 = my_nn.Sequential(
            my_nn.Linear(50 * 5 * 5, 1024),
            my_nn.ReLU(inplace=True),
            my_nn.Linear(1024, 128),
            my_nn.ReLU(inplace=True),
            # 最终判定出的是0~9这10个数字
            my_nn.Linear(128, 10)
        )

    '''
        定义前进方式
        :param self
        :param x
    '''
    def forward(self, x):
        # 首先走过前四层
        x = self.layer1(x)
        x = self.layer2(x)
        x = self.layer3(x)
        x = self.layer4(x)
        # 进入全连接层，数据需预先flatten
        x = x.view(x.size(0), -1)
        x = self.layer5(x)
        return x


# 将模型投放在CPU上运行
cnn = CNN()
device = torch.device("cpu")
cnn.to(device)

# 定义损失函数（用交叉熵损失）
loss = my_nn.CrossEntropyLoss()

# 定义优化函数（随机梯度下降，初始学习率置为0.1，动量置为0.5）
optimizer = torch.optim.SGD(cnn.parameters(), lr=0.1, momentum=0.5)

'''
    对模型进行训练
    :param counter 训练计数器
'''
def my_train(counter):
    # 整体的损失量
    total_loss = 0.0
    for batch_idx, data in enumerate(train_data_loader, 0):
        # 将输入和目标数据放在CPU上运行
        inputs, target = data
        inputs, target = inputs.to(device), target.to(device)
        optimizer.zero_grad()
        # 前馈学习
        outputs = cnn(inputs)
        # 反馈损失
        loss_unit = loss(outputs, target)
        loss_unit.backward()
        # 优化更新
        optimizer.step()
        total_loss += loss_unit.item()
        # 输出迭代过程中每100次迭代的平均损失
        if batch_idx % 100 == 99:
            print('counter %d iteration from %5d to %5d average loss:%.3f' % (
            counter, batch_idx - 99, batch_idx + 1, total_loss / 100))
            total_loss = 0.0
    torch.save(cnn, 'model{}.pth'.format(counter))


'''
    对训练出的模型进行测试
'''
def my_test():
    correct_num = 0
    total_num = 0
    # 测试时无需计算梯度
    with torch.no_grad():
        for data in test_data_loader:
            # 将输入和目标数据放在CPU上运行
            inputs, target = data
            inputs, target = inputs.to(device), target.to(device)
            outputs = cnn(inputs)
            # 获取输出数据每行最大值的下标，与target进行比较，以判断准确率
            _, predicted = torch.max(outputs.data, dim=1)
            total_num += target.size(0)
            correct_num += (predicted == target).sum().item()
    accuracy = (float(correct_num) / float(total_num)) * 100
    print("Test Accuracy:{}%".format(accuracy))


if __name__ == '__main__':
    # 进行十轮训练，每轮训练结束后都在测试集上进行测试
    for i in range(0, 10, 1):
        my_train(i)
        my_test()
