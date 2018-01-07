# -*- coding: utf-8 -*-
"""
Created on Fri Jan  5 10:42:16 2018

E-mail: xieydd@gmail.com

@author: xieydd

@description:使用SVM进行人脸识别
"""
import numpy,time  
from sklearn import datasets  
from sklearn import svm  
from sklearn import decomposition  
from sklearn import manifold  
from sklearn.cross_validation import train_test_split as tts  
from sklearn import svm,neural_network  
from sklearn.metrics import classification_report,precision_score,recall_score,f1_score  
from sklearn import pipeline  
from sklearn.preprocessing import StandardScaler,MinMaxScaler  
from sklearn.grid_search import GridSearchCV  
from matplotlib import pyplot  
import matplotlib.colors as colors

def get_data():  
    face_data=datasets.fetch_olivetti_faces()  
    #face_data=datasets.load_iris()  
    data=face_data.data  
    target=face_data.target  
    return data,target 
 
def pca(x,n):  
    pca_learner=decomposition.PCA(n_components=n)  
    x=pca_learner.fit_transform(x)  
    return x
 
def pca_svm(pca_n=10,svm_C=1):  
    t1=time.time()  
    data,target=get_data()  
    #scale_learner=StandardScaler()  
    #data=scale_learner.fit_transform(data)  
    x_train,x_test,y_train,y_test=tts(data,target,random_state=33)  
    pca_learner=decomposition.PCA(n_components=pca_n)  
    x_train=pca_learner.fit_transform(x_train)  
    svm_learner=svm.SVC(C=svm_C)  
    svm_learner.fit(x_train,y_train)  
    x_test_pre=pca_learner.transform(x_test)  
    y_test_pre=svm_learner.predict(x_test_pre)  
    # report=classification_report(y_test,y_test_pre)  
    # print 'The Main Explanied: ',numpy.sum(pca_learner.explained_variance_ratio_)  
    # print report  
    # print x_test_pre.shape,y_test_pre.shape,y_test.shape  
    ac=svm_learner.score(x_test_pre,y_test)  
    p=precision_score(y_test,y_test_pre,average='weighted')  
    r=recall_score(y_test,y_test_pre,average='weighted')  
    f1=2.0/(1.0/p+1.0/r)  
    t=time.time()-t1  
    return ac,p,r,f1,t
 
def pca_svm_time_score_compare():  
    ac_score=[]  
    p_score=[]  
    r_score=[]  
    f1_score=[]  
    tt=[]  
    stand=MinMaxScaler((20,30))  
    steps=numpy.arange(10,410,10)  
    for n in steps:  
        ac,p,r,f1,t=pca_svm(pca_n=n)  
        p_score.append(p)  
        f1_score.append(f1)  
        r_score.append(r)  
        ac_score.append(ac)  
        tt.append(t)  
    p_score_stand=stand.fit_transform(numpy.array(p_score).reshape((-1,1)))  
    r_score_stand=stand.fit_transform(numpy.array(r_score).reshape((-1,1)))  
    f1_score_stand=stand.fit_transform(numpy.array(f1_score).reshape((-1,1)))  
    ac_score_stand=stand.fit_transform(numpy.array(ac_score).reshape((-1,1)))  
    figure=pyplot.figure()  
      
      
    pyplot.subplot(2,1,1)  
    pyplot.scatter(steps,f1_score,label='f1-score',color='red',s=p_score_stand,alpha=0.7)  
    pyplot.scatter(steps,r_score,label='recall-score',color='blue',s=r_score_stand,alpha=0.7)  
    pyplot.scatter(steps,p_score,label='precision-score',color='yellow',s=f1_score_stand,alpha=0.7)  
    pyplot.scatter(steps,ac_score,label='accuracy-score',color='purple',s=ac_score_stand,alpha=0.7)  
    pyplot.xlabel('n-components')  
    pyplot.ylabel('score')  
    pyplot.legend()  
    pyplot.title('The Score Of SVM After PCA To N_components')  
    pyplot.subplot(2,1,2)  
    pyplot.plot(steps,tt,label='cost-time',color='black',marker='o')  
    # for i in range(len(tt)):  
        # pyplot.text(steps[i],ac_score[i],str(round(tt[i],1))+'s',fontdict=dict(size=10,weight='normal'))  
        # pyplot.plot([steps[i],steps[i]],[0,ac_score[i]],'--b')  
    pyplot.legend()  
    pyplot.xlabel('n-components')  
    pyplot.ylabel('time')  
    pyplot.show() 
    
# pca before svm fitting is better  
def pca_svm_pipeline():  
    #svm_C=numpy.linspace(0.5,10,10)  
    svm_C=[1]  
    pca_n_components=numpy.arange(5,200,10)  
    data,target=get_data()  
    x_train,x_test,y_train,y_test=tts(data,target,random_state=33)  
    #scale_learner=StandardScaler()  
    pca_learner=decomposition.PCA()  
    svm_learner=svm.SVC()  
    pipe=pipeline.Pipeline([('pca',pca_learner),('svm',svm_learner)])  
    gscv=GridSearchCV(pipe,  
                      {'pca__n_components':pca_n_components,'svm__C':svm_C},n_jobs=-1)  
    gscv.fit(x_train,y_train)  
    y_test_pre=gscv.predict(x_test)  
    report=classification_report(y_test,y_test_pre)  
    print(gscv.best_params_ )
    print(report)  
    target_pre=gscv.predict(data)  
    n1,n2=data.shape  
    figure=pyplot.figure()  
    L=numpy.zeros((40,))  
    xx=numpy.linspace(0,1,64)+13  
    yy=numpy.linspace(1,0,64)+13  
    xx,yy=numpy.meshgrid(xx,yy)  
    for i in range(n1):  
        k=target_pre[i]  
        g=L[k]  
        L[k]+=1  
        xx1=xx-k  
        yy1=yy-g  
        pyplot.contourf(xx1,yy1,data[i].reshape((64,64)),cmap='gray')  
        if target[i]!=target_pre[i]:  
            pyplot.scatter(numpy.mean(xx1),numpy.mean(yy1),marker='x',c='red',s=40)  
    pyplot.axis('off')  
    pyplot.grid('off')  
    pyplot.title('PCA & SVM Recongnize Faces')  
    pyplot.show()
    
if __name__=='__main__':  
    pca_svm_pipeline()  #Grid Search and show the results  
    pca_svm_time_score_compare()  #Direct Search
