import matplotlib.pyplot as plt
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn.metrics import precision_score, recall_score


def not_empty(s):
    return s != ""


if __name__ == "__main__":
    # 获取iris数据集
    iris = load_iris()

    # 获取样本特征和标签
    data = iris.data
    target = iris.target

    # 划分训练集和测试集
    data_train, data_test, target_train, target_test = train_test_split(data, target, test_size=0.3, random_state=0)
    print("total size:", len(target), "train size:", len(target_train), "test_size:", len(target_test))

    # 训练分类器
    model = SVC(kernel="linear")
    model.fit(data_train, target_train)

    # 测试和评估
    result = model.predict(data_test)
    # 精确率
    print("micro precision score:", precision_score(target_test, result, average="micro"))
    print("macro precision score:", precision_score(target_test, result, average="macro"))
    # 召回率
    print("micro recall score:", recall_score(target_test, result, average="micro"))
    print("macro recall score:", recall_score(target_test, result, average="macro"))

    # 数据可视化分析
    # 萼片长宽与标签关系散点图
    x = data[:, :2]
    y = target
    plt.scatter(x[y == 0, 0], x[y == 0, 1], color='r', marker='o')
    plt.scatter(x[y == 1, 0], x[y == 1, 1], color='g', marker='*')
    plt.scatter(x[y == 2, 0], x[y == 2, 1], color='b', marker='+')
    plt.xlabel('sepal length')
    plt.ylabel('sepal width')
    plt.savefig('visualize1.png')
    plt.clf()
    # 花瓣长宽与标签关系散点图
    x = data[:, 2:]
    y = target
    plt.scatter(x[y == 0, 0], x[y == 0, 1], color='r', marker='o')
    plt.scatter(x[y == 1, 0], x[y == 1, 1], color='g', marker='*')
    plt.scatter(x[y == 2, 0], x[y == 2, 1], color='b', marker='+')
    plt.xlabel('petal length')
    plt.ylabel('petal width')
    plt.savefig('visualize2.png')
    plt.clf()

