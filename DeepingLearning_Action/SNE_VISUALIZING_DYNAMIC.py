# -*- coding: utf-8 -*-
"""
Created on Fri Jan  5 11:41:52 2018

E-mail: xieydd@gmail.com

@author: xieydd

@description:动态sne，输出视频

"""
from sklearn import manifold  
from sklearn import decomposition  
from sklearn import datasets  
from sklearn import svm  
from sklearn.preprocessing import scale,MinMaxScaler  
from matplotlib import pyplot,colors  
import numpy,pandas  
from pandas.tools import plotting  
from pylab import mpl
import time

def test_digits():
    color=['yellow','black','green','red','blue','orange','brown','pink','purple','grey']
    data=datasets.load_digits()  
    X=data.data  
    target=data.target
      
    pyplot.subplot(1,2,2)  
    t1=time.time()  
    Y1=manifold.TSNE(2).fit_transform(data.data)
    tsne_dat=shelve.open('tsne')
    tsne_dat['data']=Y1
    t2=time.time()  
    t=t2-t1  
    print("Sklearn TSNE cost time: %s"%str(round(t,2))) 
    for i in range(10):  
        xxx1 = Y1[target == i, 0]  
        xxx2 = Y1[target == i, 1]
        
        pyplot.scatter(xxx1,xxx2,c=color[i])  
    pyplot.xlim(numpy.min(Y1)-5,numpy.max(Y1)+5)  
    pyplot.xlim(numpy.min(Y1)-5,numpy.max(Y1)+5)  
    pyplot.title('SKLEARN: %ss'%str(round(t,2)))  
    pyplot.show() 
    
  
# -*-encoding:utf-8-*-  
  
mpl.rcParams['axes.unicode_minus']=False  
mpl.rcParams['font.sans-serif']=['SimHei']  

import imageio
imageio.plugins.ffmpeg.download()
from moviepy.video.io.bindings import mplfig_to_npimage as figage  
import moviepy.editor as mpe  
import shelve
  
from scipy import spatial,sparse  
a=sparse.csc_matrix  
spatial.distance  
  
imageio.plugins.ffmpeg.download()
# data get  
data=datasets.load_digits()  
x=data.data  
y=data.target  
  
# color list  
''''' 
color=[] 
for i in colors.cnames: 
color.append(i) 
'''  
color=['yellow','black','green','red','blue','orange','brown','pink','purple','grey']  


def make_frame(t):  
    tt=int(t/0.1)  
    #print(tt)
    pyplot.clf()  
    for i in range(10):  
        xxx1 = XX[YY == i, 0]  
        xxx2 = XX[YY == i, 1]  
        print(xxx1.shape,xxx2.shape)
        pyplot.scatter(xxx1,xxx2,c=color[i])  
    #pyplot.title('SNE VISUALIZING DYNAMIC '.decode('utf-8', 'ignore'))  
    pyplot.axis('off')  
    M1=numpy.min(XX[tt])  
    M2=numpy.max(XX[tt])  
    if M1>0:  
        M1=M1*0.8  
    else:  
        M1=M1*1.2  
    if M2>0:  
        M2=M2*1.2  
    else:  
        M2=M2*0.8  
    pyplot.xlim(M1, M2)  
    pyplot.ylim(M1, M2)  
    return figage(figure1)  


test_digits()
DD=shelve.open('tsne')  
XX=DD['data']
YY=y  
N=len(XX)
DD.close()  
#figure1=pyplot.figure('SNE VISUALIZING DYNAMIC',dpi=800)  
picture=mpe.VideoClip(make_frame,duration=40)  
picture.write_videofile("tsne_0.mp4",codec='mpeg4',fps=10)

