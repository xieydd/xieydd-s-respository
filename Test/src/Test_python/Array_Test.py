#@Description: Final rate of Rap Of China
#@author xieydd xieydd@gmail.com  
#@date 2017-9-11 下午1:25:50 
from itertools import combinations,permutations
from scipy.special import comb, perm
import numpy as np


print(perm(3,2))#6.0
print(comb(3,2))#3.0


data = np.arange(0,10,1)#data = array([0,1,2,..9],dtyte=np.float);np.linspace(0,10,10,endpoint=false)0-10 10个数平均间隔不包括终点等高；等比数列np.logspace(0,2,20) 这个2是10的二次方一共20个数
print(data.shape)#reshape(column,row)可以改变数组
print(list(permutations(data,2)))#permutations(data,2)获得的是对象<itertools.permutations object at 0x000002434CCBBF68>
#[(0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (0, 8), (0, 9), (1, 0), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (2, 0), (2, 1), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (3, 0), (3, 1), (3, 2), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (4, 0), (4, 1), (4, 2), (4, 3), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (5, 0), (5, 1), (5, 2), (5, 3), (5, 4), (5, 6), (5, 7), (5, 8), (5, 9), (6, 0), (6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 7), (6, 8), (6, 9), (7, 0), (7, 1), (7, 2), (7, 3), (7, 4), (7, 5), (7, 6), (7, 8), (7, 9), (8, 0), (8, 1), (8, 2), (8, 3), (8, 4), (8, 5), (8, 6), (8, 7), (8, 9), (9, 0), (9, 1), (9, 2), (9, 3), (9, 4), (9, 5), (9, 6), (9, 7), (9, 8)]
print(list(combinations(data,2)))
#[(0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (0, 8), (0, 9), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (5, 6), (5, 7), (5, 8), (5, 9), (6, 7), (6, 8), (6, 9), (7, 8), (7, 9), (8, 9)]

#可以做一些验证
print(np.fromstring("abcdefgh",dtype=np.int))#[1684234849 1751606885]
print(np.fromstring("sasfsdfsd",dtype=np.int8))#[115  97 115 102 115 100 102 115 100]
print(np.fromstring("abcdefgh",dtype=np.int16))#[25185 25699 26213 26727]
print(np.fromstring("abcdefgh",dtype=np.float))#[  8.54088322e+194]