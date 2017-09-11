#@Description: Final rate of Rap Of China
#@author xieydd xieydd@gmail.com  
#@date 2017-9-11 上午10:25:50 


from scipy.special import comb
import operator 

def cal_num(score_num): 
    a = b = c = range(0, 51) 
    result = 0 
    for x in a: 
        for y in b: 
            for z in c: 
                if x + y + z == score_num: 
                    result += 1 
    return result

if __name__ == '__main__':
    all_num = 2 ** 100 * 51**3
    print("所有可能的情况：%d" % all_num)
    result = {}
    for score_sum in range(0,126):
        num = 0
        for score_num1 in range(0,101):
            num1 = comb(100,score_num1)#阶乘
            if score_num1 < score_sum:
                score_num2 = score_sum - score_num1
                num2 = cal_num(score_num2)
                num += num1*num2
        if score_sum == 125:
            print("Gai:PGOne票数之比为125:125的情况总数： %d" % num)
            print("Gai:PGOne票数之比为125:125的概率:%8f" % (float(num)/all_num))
        key_name = str(score_sum)+":"+str(250-score_sum)
        result[key_name] = float(num)/all_num
    result_sort = sorted(result.items(),key=operator.itemgetter(1),reverse=True)
    print("Gai:PGOne最终得票的概率由高到底依次是:")
    for each in result_sort:
        print(each[0],"\t",each[1])