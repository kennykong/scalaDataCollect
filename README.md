## Scala提取电影评分数据，并取出得分最高的前十条

### 1.背景

这是某公司实战面试题。面试官给出了一个URL，这个URL存的是一系列电影评分的csv表格文件，要求用Scala提取电影评分数据，并取出得分最高的前十条。

当然我看了一下数据里面有一条数据和其他不一样，是带双引号的，而且双引号里面还有逗号，这个逗号很容易在处理过程中与csv中的逗号混淆，怎么办呢？

我写了2个版本，第一个版本是在半小时内写出来的，所谓常规方法，Read。

还有一个是在之后深思熟虑的改进版本，ReadFun。

---

### 2. 普通版本，自然写法
第一次思路是：
1. 通过url读取csv, 作为一串字符串
2. 把csv的每一行构建成一个case class - Movie, 包含三个字段：name, time, score
3. 用一个可变数组ListBuffer来接收每一行数据
4. 把带引号那一行特殊处理
5. 去掉表头
6. 对score排序，取前10

---

### 3. 改进版本改进了什么？
第一次小改：
1. 把ListBuffer这个可变集合改成更加函数式的 for...yield 表达式生成集合
2. 把Movie这个类去掉，换成scala内建的Tuple3，这个Tuple是scala特有的，处理行数据特别方便，特别给力

第二次改动：
1. 把原来特殊处理的带引号那一行，用通用处理的方式处理，思路是：把引号内的逗号（,）先处理成其他符号如下划线（_）, 这样不和csv的分隔符逗号（,）混淆, 然后再做分隔，再做后继处理。

第三次改动：
1. 最初的想法是想把sortWith之后再takeN的做法替换成topN一个方法搞定，这样可以节省一点sort整个List的开销，topN只要取前10个最大的，后面就不用排了，所谓 "用多少排多少"， 一点不浪费。
2. 参照了外国小哥的topN的写法，但是发现他的写法里面还是用了sort，这样最初的 "用多少排多少" 的想法没有实现。
3. 还有一个更要命的是他的方法不够"通用"，他自己给了一个Score的实现，我用的是Tuple3，难道要再按照他的算法写一个Tuple3的实现？这太重复代码工作量了
4. 还有一个不美的地方是，我希望直接通过list后面通过.连缀的方式来实现topN，他这个也做不到
5. 最后scala的"杀手锏"不得不使出来了，就是类型类（TypeClass），类型类其实是 **函数包含泛型的参数在scala的类型实现** ，类型类的实现请参考下[learning-scalaz](http://eed3si9n.com/learning-scalaz/polymorphism.html)的ad-hoc polymorphism的例子。
6. 然后在list后面通过.连缀的方式是implicit的功能之一，这个programming in scala有详细介绍的，demo包里面有个例子RichFile，不再赘述。
7. 当然5，6两个功能要结合还是花了一些时间的，调试起来需要理一理思路，这个需要尝试和领悟。

---

### 4. 不足
这样的写法的不足就是缺少类型推断的能力，比如说我用Tuple就是这样：
```
list.topNWith(10, _._2)
```
我用Movie.score就是这样：
```
list.topNWith(10, _.score)
```

以后再做改进，待续...

---

### 5. 完成对4的改进
已完成，在topn1包里面，运行ReadFun1

---

### 参考
1. 从字符串里面提取出双引号的内容
https://blog.csdn.net/u010102284/article/details/17246413

2. 老外写的不够通用的TopN
https://blog.genuine.com/2018/06/generic-top-n-elements-in-scala/

3. 如何为已有类库扩展方法
https://blog.csdn.net/qq_36330643/article/details/77866144

4. TypeClass 和 ad-hoc polymorphism
http://eed3si9n.com/learning-scalaz/polymorphism.html