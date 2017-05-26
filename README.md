# Simulator

## 文件结构
  icon文件夹内为gui所用的透明图表，src内为代码，其中每个文件夹为一个package。目前已基本完成了dialog与gui包的内容，均为实现gui所依赖的类。main包还未完成
  
## 工程结构
  main模块中的Tomasulo类为顶层模块，目前在其中定义了一个成员变量mainGui，可以通过这个成员变量调用gui的API，具体内容在gui包的MainGui类中有注释。MainGui中同样还定义了一些函数，以调用其它模块的API，主要是将Gui中获得的一些参数传到后端，具体同样间其中的注释。这些函数需要在完成后端相应内容后进行填写。
