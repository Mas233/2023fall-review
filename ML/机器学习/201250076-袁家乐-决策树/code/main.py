from math import log
from numpy import inf
import copy
from collections import Counter
import csv
from sklearn.metrics import f1_score,precision_score

# 训练数据集
train_data_set = []
# 训练属性列表，其中UsefulCount为连续值，sideEffects为离散值
train_labels = ['condition', 'UsefulCount', 'sideEffects']
# 记录属性是否为离散值
labels_is_discrete = [True, False, True]
# 分类名列表
class_tags = ['1', '2', '3', '4', '5']
# 验证数据集
validation_data_set = []
# 测试数据集
test_data_set = []

# 加载、预处理（清洗、去掉无关列）三份csv文件
def data_pre_process():
    """
        加载、预处理（清洗、去掉无关列）三份csv文件
    """
    # 读入training.csv，清洗掉无关列，对condition列做一定的清洗
    # 只保留condition,usefulCount和sideEffects
    with open('training.csv', 'r', encoding='utf-8') as train_csv:
        reader = csv.DictReader(train_csv)
        for row in reader:
            clean_line = ['', '', '', '']
            clean_line[0] = row.get('condition')
            if clean_line[0].find('users found this comment helpful.') != -1:
                if clean_line[0][0:1] == '0':
                    clean_line[0] = 'comment helpless'
                else:
                    clean_line[0] = 'comment helpful'
            if clean_line[0] == '':
                clean_line[0] = 'no condition'
            clean_line[1] = row.get('usefulCount')
            clean_line[2] = row.get('sideEffects')
            clean_line[3] = row.get('rating')
            train_data_set.append(clean_line)
    print('training.csv loaded: SUCCESS!')

    # 读入validation.csv，清洗掉无关列，对condition列做一定的清洗
    # 只保留condition,usefulCount和sideEffects
    with open('validation.csv', 'r', encoding='utf-8') as validation_csv:
        reader = csv.DictReader(validation_csv)
        for row in reader:
            clean_line = ['', '', '', '']
            clean_line[0] = row.get('condition')
            if clean_line[0].find('users found this comment helpful.') != -1:
                if clean_line[0][0:1] == '0':
                    clean_line[0] = 'comment helpless'
                else:
                    clean_line[0] = 'comment helpful'
            if clean_line[0] == '':
                clean_line[0] = 'no condition'
            clean_line[1] = row.get('usefulCount')
            clean_line[2] = row.get('sideEffects')
            clean_line[3] = row.get('rating')
            validation_data_set.append(clean_line)
    print('validation.csv loaded: SUCCESS!')

    # 读入testing.csv，保留全部内容，最后一列rating目前为空串
    with open('testing.csv', 'r', encoding='utf-8') as test_csv:
        reader = csv.DictReader(test_csv)
        for row in reader:
            clean_line = ['', '', '', '', '', '', '', '']
            clean_line[0] = row.get('recordId')
            clean_line[1] = row.get('drugName')
            clean_line[2] = row.get('condition')
            clean_line[3] = row.get('reviewComment')
            clean_line[4] = row.get('date')
            clean_line[5] = row.get('usefulCount')
            clean_line[6] = row.get('sideEffects')
            clean_line[7] = row.get('rating')
            test_data_set.append(clean_line)
    print('testing.csv loaded: SUCCESS!')


# 计算信息熵
def calculate_entropy(dataset):
    """
        计算信息熵
        :param dataset: 数据集
        :return: float 信息熵的数值
    """
    dataset_len = len(dataset)
    # 计算各分类的样本数
    class_counts = {}
    for data in dataset:
        tmp_class = data[-1]
        if tmp_class not in class_counts.keys():
            class_counts[tmp_class] = 0
        class_counts[tmp_class] += 1
    ans = 0.0
    for key in class_counts:
        rate = float(class_counts[key]) / float(dataset_len)
        ans = ans - rate * log(rate, 2)
    return ans


# 计算连续值属性的最佳划分值，同时计算出信息增益和信息增益率
def calculate_best_split_value(dataset, index):
    """
        计算连续值属性的最佳划分值，同时计算出信息增益和信息增益率
        :param dataset: 数据集
        :param index: 属性下标
        :return: float 最佳划分值，float 最佳划分值下计算出的信息增益, float 最佳划分值下计算出的信息增益率
    """
    # 对指定的属性列做排序和去重
    label_list = [int(data[index]) for data in dataset]
    sorted_unique_labels = sorted(list(set(label_list)))
    for i in range(0, len(sorted_unique_labels), 1):
        sorted_unique_labels[i] = str(sorted_unique_labels[i])
    # 若该属性列只有一个取值，则无需计算最佳划分值
    if len(sorted_unique_labels) == 1:
        return -999.0, 0.0, 0.0
    # 计算最佳划分值及其信息增益、信息增益率
    ans_gain = calculate_entropy(dataset)
    iv = 0.0
    best_split_value = -1.0
    min_num = inf
    dataset_len = len(dataset)
    for i in range(0, len(sorted_unique_labels) - 1, 1):
        split_value = (float(sorted_unique_labels[i]) + float(sorted_unique_labels[i + 1])) / 2
        # 以此划分值划分出左右两边的数据集
        left_dataset = []
        right_dataset = []
        for data in dataset:
            if float(data[index]) < split_value:
                left_dataset.append(data)
            elif float(data[index]) > split_value:
                right_dataset.append(data)
        # 由于整体的信息熵是一致的，此处仅需做数据间的比较，故只需计算信息熵的加权和即可
        left_weight = float(len(left_dataset)) / float(dataset_len)
        right_weight = float(len(right_dataset)) / float(dataset_len)
        num = left_weight * calculate_entropy(left_dataset) + right_weight * calculate_entropy(right_dataset)
        if num < min_num:
            min_num = num
            best_split_value = split_value
            iv = -1 * (left_weight * log(left_weight, 2) + right_weight * log(right_weight, 2))
    ans_gain = ans_gain - min_num
    if iv == 0.0:
        iv = 0.00000001
    ans_gain_ratio = ans_gain / iv
    return best_split_value, ans_gain, ans_gain_ratio


# 计算用离散值属性划分样本集获得的信息增益和信息增益率
def calculate_gain_ratio_discrete(dataset, index):
    """
        计算用离散值属性划分样本集获得的信息增益和信息增益率
        :param dataset: 数据集
        :param index: 属性下标
        :return: float 信息增益的数值, float 信息增益率的数值
    """
    ans_gain = calculate_entropy(dataset)
    iv = 0.0
    dataset_len = len(dataset)
    # 对指定的属性列做排序和去重
    label_list = [data[index] for data in dataset]
    sorted_unique_labels = sorted(list(set(label_list)))
    if len(sorted_unique_labels) == 1:
        return 0.0, 0.0
    for label in sorted_unique_labels:
        # 依据属性值划分数据集，计算各自的带权信息熵
        sub_dataset = []
        for data in dataset:
            if data[index] == label:
                sub_dataset.append(data)
        sub_weight = float(len(sub_dataset)) / float(dataset_len)
        ans_gain -= sub_weight * calculate_entropy(sub_dataset)
        iv -= sub_weight * log(sub_weight, 2)
    if iv == 0.0:
        iv = 0.00000001
    ans_gain_ratio = ans_gain / iv
    return ans_gain, ans_gain_ratio


# 选择最佳的分类属性
def choose_best_label(dataset, is_discrete):
    """
        选择最佳的分类属性
        先从候选划分属性中找出信息增益高于平均水平的属性，再从中选择信息增益率最高的
        :param dataset: 数据集
        :param is_discrete: 属性是否为离散值
        :return int 最佳划分的属性下标，float 针对连续值属性的最佳划分值
    """
    # 最佳划分的属性下标
    best_index = -1
    # 最佳划分的信息增益率
    best_gain_ratio = 0.0
    # 针对连续值属性的的最佳划分值
    best_split_value = -999.0
    # 计算信息增益以获取平均信息增益，同时计算信息增益率，针对连续属性值划分其
    avg_gain = 0.0
    split_value_list = [-999.0 for k in range(len(is_discrete))]
    gain_list = [0.0 for k in range(len(is_discrete))]
    gain_ratio_list = [0.0 for k in range(len(is_discrete))]
    for i in range(0, len(is_discrete), 1):
        # 处理离散值属性
        if is_discrete[i]:
            info_gain, info_gain_ratio = calculate_gain_ratio_discrete(dataset, i)
            avg_gain += info_gain
            gain_list[i] = info_gain
            gain_ratio_list[i] = info_gain_ratio
        # 处理连续值属性
        else:
            split_value, info_gain, info_gain_ratio = calculate_best_split_value(dataset, i)
            if split_value != -999.0:
                avg_gain += info_gain
                split_value_list[i] = split_value
                gain_list[i] = info_gain
                gain_ratio_list[i] = info_gain_ratio
    avg_gain = avg_gain / len(is_discrete)

    # 筛选出最佳的分类属性
    for i in range(0, len(is_discrete), 1):
        if gain_ratio_list[i] > best_gain_ratio and gain_list[i] > avg_gain:
            best_index = i
            best_split_value = split_value_list[i]
    return best_index, best_split_value


# 构建C4.5决策树
def create_my_tree(dataset, labels, is_discrete):
    """
        构建C4.5决策树
        :param dataset: 数据集
        :param labels: 属性列表
        :param is_discrete: 属性是否是离散值列表
        :return: tree C4.5决策树
    """
    # 取训练集的最后一列形成类别列表
    class_list = [data[-1] for data in dataset]

    # 如果当前样本均属于同一个类别，则将此类别标记为叶节点并返回
    if class_list.count(class_list[0]) == len(class_list):
        return (class_list[0], len(dataset), 0)

    # 如果属性列表已然为空，则返回出现次数最多的类别
    if len(dataset[0]) == 1:
        majority_class = Counter(class_list).most_common()
        return (majority_class[0][0], majority_class[0][1], len(dataset) - majority_class[0][1])

    # 选择最佳的分类属性
    best_index, best_split_value = choose_best_label(dataset, is_discrete)
    if best_index == -1:
        # 若无最佳的分类属性，则返回出现次数最多的类别
        majority_class = Counter(class_list).most_common()
        return (majority_class[0][0], majority_class[0][1], len(dataset) - majority_class[0][1])

    # 最佳分类属性是离散值属性的情形
    if is_discrete[best_index] or best_split_value == -999.0:
        label_name = labels[best_index]
        my_tree = {label_name: {}}
        # 针对此属性完成分类后，将此属性从列表中去除掉
        new_labels = copy.copy(labels)
        new_is_discrete = copy.copy(is_discrete)
        del (new_labels[best_index])
        del (new_is_discrete[best_index])
        # 对该属性的所有取值建立子树
        label_values = [data[best_index] for data in dataset]
        unique_values = set(label_values)
        for value in unique_values:
            sub_labels = new_labels[:]
            sub_is_discrete = new_is_discrete[:]
            sub_dataset = []
            for data in dataset:
                if data[best_index] == value:
                    # 新构造的子数据集需要去掉已完成分类的属性列
                    tmp_data_line = data[:best_index]
                    tmp_data_line.extend(data[best_index + 1:])
                    sub_dataset.append(tmp_data_line)
            my_tree[label_name][value] = create_my_tree(sub_dataset, sub_labels, sub_is_discrete)

    # 最佳分类属性是连续值属性的情形
    else:
        label_name = labels[best_index] + '<' + str(best_split_value)
        my_tree = {label_name: {}}
        # 连续值属性不应当被去除
        new_labels = labels[:]
        new_is_discrete = is_discrete[:]
        # 分别构建左右子树
        value_left = 'Y'
        value_right = 'N'
        sub_dataset_left = []
        sub_dataset_right = []
        for data in dataset:
            if float(data[best_index]) < best_split_value:
                sub_dataset_left.append(data)
            elif float(data[best_index]) > best_split_value:
                sub_dataset_right.append(data)
        my_tree[label_name][value_left] = create_my_tree(sub_dataset_left, new_labels, new_is_discrete)
        my_tree[label_name][value_right] = create_my_tree(sub_dataset_right, new_labels, new_is_discrete)

    return my_tree


# 对数据做分类
def do_classify(tree, class_list, labels, is_discrete, data):
    """
        对数据做分类
        :param tree: C4.5决策树
        :param class_list: 类别列表
        :param labels: 属性列表
        :param is_discrete: 属性是否为离散值列表
        :param data: 待分类的数据
        :return: dictionary 各种类别的分类权值
    """
    # 获取根节点
    root_str = list(tree.keys())[0]
    root_label = root_str
    # 判断是否存在小于号（是否为连续值属性）
    less_index = str(root_str).find('<')
    if less_index > -1:
        root_label = str(root_str)[:less_index]
    next_tree = tree[root_str]
    label_index = labels.index(root_label)
    # 分类结果
    class_result = {}
    for tag in class_list:
        class_result[tag] = 0.0
    # 针对每个子树做遍历
    for key in next_tree.keys():
        # 离散值属性的情形
        if is_discrete[label_index]:
            # 进入子树中
            if data[label_index] == key:
                # 若为非叶结点，继续遍历
                if type(next_tree[key]).__name__ == 'dict':
                    sub_class_result = do_classify(next_tree[key], class_list, labels, is_discrete, data)
                    for key_tmp in class_result.keys():
                        class_result[key_tmp] += sub_class_result[key_tmp]
                # 若为叶结点，保存结果
                else:
                    for key_tmp in class_result.keys():
                        if key_tmp == next_tree[key][0]:
                            class_result[key_tmp] += next_tree[key][1]
                        else:
                            class_result[key_tmp] += float(next_tree[key][2])/4
        # 连续值属性的情形
        else:
            split_value = float(str(root_str)[less_index + 1:])
            # 进入左子树
            if float(data[label_index]) <= split_value and key == 'Y':
                # 若为非叶结点，继续遍历
                if type(next_tree['Y']).__name__ == 'dict':
                    sub_class_result = do_classify(next_tree['Y'], class_list, labels, is_discrete, data)
                    for key_tmp in class_result.keys():
                        class_result[key_tmp] += sub_class_result[key_tmp]
                # 若为叶结点，保存结果
                else:
                    for key_tmp in class_result.keys():
                        if key_tmp == next_tree[key][0]:
                            class_result[key_tmp] += next_tree['Y'][1]
                        else:
                            class_result[key_tmp] += float(next_tree['Y'][2])/4
            # 进入右子树
            elif float(data[label_index]) > split_value and key == 'N':
                # 若为非叶结点，继续遍历
                if type(next_tree['N']).__name__ == 'dict':
                    sub_class_result = do_classify(next_tree['N'], class_list, labels, is_discrete, data)
                    for key_tmp in class_result.keys():
                        class_result[key_tmp] += sub_class_result[key_tmp]
                # 若为叶结点，保存结果
                else:
                    for key_tmp in class_result.keys():
                        if key_tmp == next_tree[key][0]:
                            class_result[key_tmp] += next_tree['N'][1]
                        else:
                            class_result[key_tmp] += float(next_tree['N'][2])/4
    return class_result


if __name__ == '__main__':
    # 数据预处理
    data_pre_process()
    # 构建分类树
    my_tree = create_my_tree(train_data_set, train_labels, labels_is_discrete)
    print('C4.5 tree built: SUCCESS!')

    # 使用验证集进行验证
    true_class_list = []
    predict_class_list=[]
    for data in validation_data_set:
        # 对每条验证集数据做分类，生成预测的分类结果
        class_list = ['1', '2', '3', '4', '5']
        labels = ['condition', 'UsefulCount', 'sideEffects']
        is_discrete = [True, False, True]
        true_class_list.append(data[-1])
        class_result = do_classify(my_tree, class_list, labels, is_discrete, data)
        ans = ''
        num = -1
        for key in class_result.keys():
            if class_result.get(key) > num:
                num = class_result.get(key)
                ans = key
        predict_class_list.append(ans)
    # 分别使用Micro F1和Macro F1进行验证集评估
    micro_f1=f1_score(y_true=true_class_list,y_pred=predict_class_list,average="micro")
    macro_f1=f1_score(y_true=true_class_list,y_pred=predict_class_list,average="macro")

    print('Micro F1: {}'.format(micro_f1))
    print('Macro F1: {}'.format(macro_f1))

    # 对测试集进行分类
    for data in test_data_set:
        class_list = ['1', '2', '3', '4', '5']
        labels = ['condition', 'UsefulCount', 'sideEffects']
        is_discrete = [True, False, True]
        tmp_data_line = ['', data[5], data[6]]
        # 对condition列做一定的清洗
        tmp_data_line[0] = data[2]
        if tmp_data_line[0].find('users found this comment helpful.') != -1:
            if tmp_data_line[0][0:1] == '0':
                tmp_data_line[0] = 'comment helpless'
            else:
                tmp_data_line[0] = 'comment helpful'
        if tmp_data_line[0] == '':
            tmp_data_line[0] = 'no condition'
        class_result = do_classify(my_tree, class_list, labels, is_discrete, tmp_data_line)
        ans = ''
        num = -1
        for key in class_result.keys():
            if class_result.get(key) > num:
                num = class_result.get(key)
                ans = key
        data[7] = ans
    print('test data classified: SUCCESS!')

    # 测试结果写入文件中
    with open("testing_result.csv", "w", encoding="utf-8", newline="") as f:
        csv_writer = csv.writer(f)
        csv_writer.writerow(
            ["recordId", "drugName", "condition", "reviewComment", "date", "usefulCount", "sideEffects", "rating"])
        for data in test_data_set:
            csv_writer.writerow(data)
        print('test result written: SUCCESS!')
        f.close()
