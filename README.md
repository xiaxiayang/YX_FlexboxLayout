# YX_FlexboxLayout
android FlexboxLayout布局使用介绍
### 1.什么是 flexboxLayout 布局？
[https://github.com/google/flexbox-layout](https://github.com/google/flexbox-layout) 项目简介中是这样一句话来概括 flexboxLayout 的：
 > FlexboxLayout is a library project which brings the similar capabilities of CSS Flexible Box Layout Module to Android.
 > FlexboxLayout 是一个在 Android 上实现  CSS 的 弹性盒状布局 模块的库

有前端基础的同学估计都知道 CSS 中这个布局，用来为盒状模型提供最大的灵活性。因为 android 中这个库属性和 CSS 中 都一样，并且阮一峰老师写的前端知识真的很通俗易懂，所以这里的介绍大多来自 [Flex 布局教程](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html)。
![3.png](https://i.loli.net/2018/10/24/5bd08f8c4c492.png)
采用 Flex 布局的元素，称为 Flex 容器（flex container），简称"容器"。它的所有子元素自动成为容器成员，称为 Flex 项目（flex item），简称"项目"。

容器默认存在两根轴：**水平的主轴**（main axis）和**垂直的交叉轴**（cross axis）。这里与 react native 相反，与前端 CSS 保持一致。

主轴的开始位置（与边框的交叉点）叫做main start，结束位置叫做main end；交叉轴的开始位置叫做cross start，结束位置叫做cross end

项目默认沿主轴排列。单个项目占据的主轴空间叫做 main size，占据的交叉轴空间叫做 cross size。

### 2.容器的属性 （FlexboxLayout 属性介绍）
这里说的容器也就是上面的采用了 Flex 布局的元素，在 android 中也就是引用了 FlexboxLayout 的 控件。即 FlexboxLayout 控件支持的属性。主要属性有：
![4.png](https://i.loli.net/2018/10/24/5bd08f8d938c3.png)
各个属性的详细含义这里就不再赘述，阮一峰老师 [这篇文章](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html) 写的超级棒，图文并茂，很容易理解，推荐大家看一下。
### 3.项目的属性 （子 View 属性介绍）
设置被 FlexboxLayout 包裹的子 View 的属性，因为 android 中的 flexboxLayout 布局 和 CSS 中 flex 布局关于子 View 属性有些差异，所以这里详细说明下，取值如下：

 - layout_order (integer)
 - layout_flexGrow (float)
 - layout_flexGrow (float)
 - layout_alignSelf
 - layout_flexBasisPercent (fraction)
 - layout_minWidth / layout_minHeight (dimension)
 - layout_maxWidth / layout_maxHeight (dimension)
 - layout_wrapBefore (boolean)

下面来看一下github文档中对这些属性的描述
#### 3.1 layout_order
这个属性可以改变布局子视图的顺序。默认情况下，子元素的显示和布局顺序与布局XML中的顺序相同。如果没有指定，则将1设置为默认值( CSS 中默认值为 0) ，**数值越小，排列越靠前**。
![5.gif](https://i.loli.net/2018/10/24/5bd08f924c38c.gif)
看下文档中的这张图，可以看到将 “2” 号 View 的  layout_order 属性设置为 -1时，由于其他的 View 默认都是 1，所以 “2” 号 view会排在最前面，同理，将 “2” 号 View 的 layout_order 的属性值设为 2 时，比其他默认值 1 都大，所以会排在最后。

####  3.2 layout_flexGrow

这个属性类似于 LinearLayout 中的 **layout_weight** 属性，如果没有指定，则将 0 设置为默认值。如果果同一 flex 行中的多个子 View 有正的 layout_flexGrow 值，那么剩余的空闲空间将根据它们声明的 layout_flexGrow 值的比例分布。
### 3.3 layout_flexShrink 
该属性定义了子 View 的缩小比例，默认为 1，即如果空间不足，该子 View 将缩小。
如果所有子 View 的 layout_flexShrink 属性都为 1，当空间不足时，都将等比例缩小。如果一个项目的 layout_flexShrink 属性为0，其他子View都为 1，则空间不足时，layout_flexShrink 属性为 0 的不缩小。
![6.gif](https://i.loli.net/2018/10/24/5bd08f8ec1d4a.gif)
看一下文档中的这张图，开始设置所有子 view 的 layout_flexShrink 属性为1，添加子 view 的时候所有子 view 等比缩小，但是如果设置 layout_flexShrink 属性值为 0，子 view 将会按照原有比例显示，不缩小。

#### 3.4  layout_alignSelf
layout_alignSelf 属性允许单个子 View 有与其他 View 不一样的对齐方式，可覆盖 align-items 属性。默认值为 auto，表示继承父元素的 align-items 属性，如果没有父元素，则等同于 stretch。
该属性可能取 6 个值，除了 auto，其他都与 align-items 属性完全一致

#### 3.5  layout_flexBasisPercent 
flex-layout_flexBasisPercent 属性定义了在分配多余空间之前，子 View 占据的主轴空间（main size）。根据这个属性来计算主轴是否有多余空间。它的默认值为 -1，即不设置，采用子 View 的本来大小。

如果设置了这个值，layout_width (或 layout_height )中指定的长度将被该属性的计算值覆盖。这个属性只有在父 View 的长度是确定的时候才有效(测量模式是 MeasureSpec.EXACTLY 模式下)。
并且该属性值只接受百分比值。
![7.gif](https://i.loli.net/2018/10/24/5bd090209b72d.gif)
可以分析下文档中的这张图：可以看到，如果把中间子 View 的这个属性值设为 50%  或 90%，那么这个 View 将占据主轴 50% 或 90% 的空间，然后剩余 View 会看有没有剩余空间换行。如果设置为 -1 默认值，那么将占据给定的大小。
#### 3.6  layout_minWidth / layout_minHeight
这个属性设置了子 View 的最小的宽和高。在 layout_flexShrink 模式下，再怎么缩小也不会小于这个值
#### 3.7 layout_maxWidth / layout_maxHeight
这个属性设置了子 View 的最大的宽和高。在 layout_flexGrow 模式下，再怎么放大也不会大于这个值
#### 3.8 layout_wrapBefore 
这个属性使得子 View 可以强制换行，不管在 main size 剩余空间有多少。这种对于类似 grid 网格布局中特殊设置某一个 item 布局特别有用。
这个属性是 CSS 中没有的属性。该属性在 flex_wrap 属性值 为 nowrap（不换行）的时候是无效的。
该属性结束 boolean 变量，默认 false，即不强制换行
![9.gif](https://i.loli.net/2018/10/24/5bd0901f8e4a8.gif)
分析下文档中的这张图，“5” 号和 “6” 号 View 设置 layout_wrapBefore 属性为ture 的时候，不管前面剩余多少空间，都会强制换行

到这里，flexboxLayout 基本属性就介绍完毕了。

然后再来介绍一下跟 recycleView 结合使用。
#### 4.  高能：与 RecyclewView 结合使用
Flexbox 能够作为一个 LayoutManager(FlexboxlayoutManager) 用在 RecyclerView 里面，这也就意味着你可以在一个有大量 Item 的可滚动容器里面使用 Flexbox，提高性能。具体使用示例：

```
        //设置主轴方向为横轴
        FlexboxLayoutManager manager = new FlexboxLayoutManager(this, FlexDirection.ROW);
        //设置item沿主轴方向的位置
        manager.setJustifyContent(JustifyContent.CENTER);
        //设置item 沿次轴方向的位置
        manager.setAlignItems(AlignItems.CENTER);

        recycleView.setLayoutManager(manager);
        centerGridAdapter = new CenterGridAdapter(items, this);
        recycleView.setAdapter(centerGridAdapter);
```
可以看到跟 RecycleView 的其他 manager 使用一样，只需设置 manager 属性即可，属性值为上面叙述的几个容器的属性。
如果想对某个 item 进行单独的设置，可以在 adapter 中去设置，设置示例代码为：

```
 ViewGroup.LayoutParams lp =  holder.itemLL.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams)  holder.itemLL.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }
```
我这里是设置每个 item 有个权重（相当于 Linearlayout 的 weight 属性），所以会按比例分配 item 的宽，而不是我布局中设定的固定宽高。看下效果：
![10.png](https://i.loli.net/2018/10/24/5bd0901cb94cb.png)

### 1.什么是 flexboxLayout 布局？
[https://github.com/google/flexbox-layout](https://github.com/google/flexbox-layout) 项目简介中是这样一句话来概括 flexboxLayout 的：
 > FlexboxLayout is a library project which brings the similar capabilities of CSS Flexible Box Layout Module to Android.
 > FlexboxLayout 是一个在 Android 上实现  CSS 的 弹性盒状布局 模块的库

有前端基础的同学估计都知道 CSS 中这个布局，用来为盒状模型提供最大的灵活性。因为 android 中这个库属性和 CSS 中 都一样，并且阮一峰老师写的前端知识真的很通俗易懂，所以这里的介绍大多来自 [Flex 布局教程](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html)。
![3.png](https://i.loli.net/2018/10/24/5bd08f8c4c492.png)
采用 Flex 布局的元素，称为 Flex 容器（flex container），简称"容器"。它的所有子元素自动成为容器成员，称为 Flex 项目（flex item），简称"项目"。

容器默认存在两根轴：**水平的主轴**（main axis）和**垂直的交叉轴**（cross axis）。这里与 react native 相反，与前端 CSS 保持一致。

主轴的开始位置（与边框的交叉点）叫做main start，结束位置叫做main end；交叉轴的开始位置叫做cross start，结束位置叫做cross end

项目默认沿主轴排列。单个项目占据的主轴空间叫做 main size，占据的交叉轴空间叫做 cross size。

### 2.容器的属性 （FlexboxLayout 属性介绍）
这里说的容器也就是上面的采用了 Flex 布局的元素，在 android 中也就是引用了 FlexboxLayout 的 控件。即 FlexboxLayout 控件支持的属性。主要属性有：
![4.png](https://i.loli.net/2018/10/24/5bd08f8d938c3.png)
各个属性的详细含义这里就不再赘述，阮一峰老师 [这篇文章](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html) 写的超级棒，图文并茂，很容易理解，推荐大家看一下。
### 3.项目的属性 （子 View 属性介绍）
设置被 FlexboxLayout 包裹的子 View 的属性，因为 android 中的 flexboxLayout 布局 和 CSS 中 flex 布局关于子 View 属性有些差异，所以这里详细说明下，取值如下：

 - layout_order (integer)
 - layout_flexGrow (float)
 - layout_flexGrow (float)
 - layout_alignSelf
 - layout_flexBasisPercent (fraction)
 - layout_minWidth / layout_minHeight (dimension)
 - layout_maxWidth / layout_maxHeight (dimension)
 - layout_wrapBefore (boolean)

下面来看一下github文档中对这些属性的描述
#### 3.1 layout_order
这个属性可以改变布局子视图的顺序。默认情况下，子元素的显示和布局顺序与布局XML中的顺序相同。如果没有指定，则将1设置为默认值( CSS 中默认值为 0) ，**数值越小，排列越靠前**。
![5.gif](https://i.loli.net/2018/10/24/5bd08f924c38c.gif)
看下文档中的这张图，可以看到将 “2” 号 View 的  layout_order 属性设置为 -1时，由于其他的 View 默认都是 1，所以 “2” 号 view会排在最前面，同理，将 “2” 号 View 的 layout_order 的属性值设为 2 时，比其他默认值 1 都大，所以会排在最后。

####  3.2 layout_flexGrow

这个属性类似于 LinearLayout 中的 **layout_weight** 属性，如果没有指定，则将 0 设置为默认值。如果果同一 flex 行中的多个子 View 有正的 layout_flexGrow 值，那么剩余的空闲空间将根据它们声明的 layout_flexGrow 值的比例分布。
### 3.3 layout_flexShrink 
该属性定义了子 View 的缩小比例，默认为 1，即如果空间不足，该子 View 将缩小。
如果所有子 View 的 layout_flexShrink 属性都为 1，当空间不足时，都将等比例缩小。如果一个项目的 layout_flexShrink 属性为0，其他子View都为 1，则空间不足时，layout_flexShrink 属性为 0 的不缩小。
![6.gif](https://i.loli.net/2018/10/24/5bd08f8ec1d4a.gif)
看一下文档中的这张图，开始设置所有子 view 的 layout_flexShrink 属性为1，添加子 view 的时候所有子 view 等比缩小，但是如果设置 layout_flexShrink 属性值为 0，子 view 将会按照原有比例显示，不缩小。

#### 3.4  layout_alignSelf
layout_alignSelf 属性允许单个子 View 有与其他 View 不一样的对齐方式，可覆盖 align-items 属性。默认值为 auto，表示继承父元素的 align-items 属性，如果没有父元素，则等同于 stretch。
该属性可能取 6 个值，除了 auto，其他都与 align-items 属性完全一致

#### 3.5  layout_flexBasisPercent 
flex-layout_flexBasisPercent 属性定义了在分配多余空间之前，子 View 占据的主轴空间（main size）。根据这个属性来计算主轴是否有多余空间。它的默认值为 -1，即不设置，采用子 View 的本来大小。

如果设置了这个值，layout_width (或 layout_height )中指定的长度将被该属性的计算值覆盖。这个属性只有在父 View 的长度是确定的时候才有效(测量模式是 MeasureSpec.EXACTLY 模式下)。
并且该属性值只接受百分比值。
![7.gif](https://i.loli.net/2018/10/24/5bd090209b72d.gif)
可以分析下文档中的这张图：可以看到，如果把中间子 View 的这个属性值设为 50%  或 90%，那么这个 View 将占据主轴 50% 或 90% 的空间，然后剩余 View 会看有没有剩余空间换行。如果设置为 -1 默认值，那么将占据给定的大小。
#### 3.6  layout_minWidth / layout_minHeight
这个属性设置了子 View 的最小的宽和高。在 layout_flexShrink 模式下，再怎么缩小也不会小于这个值
#### 3.7 layout_maxWidth / layout_maxHeight
这个属性设置了子 View 的最大的宽和高。在 layout_flexGrow 模式下，再怎么放大也不会大于这个值
#### 3.8 layout_wrapBefore 
这个属性使得子 View 可以强制换行，不管在 main size 剩余空间有多少。这种对于类似 grid 网格布局中特殊设置某一个 item 布局特别有用。
这个属性是 CSS 中没有的属性。该属性在 flex_wrap 属性值 为 nowrap（不换行）的时候是无效的。
该属性结束 boolean 变量，默认 false，即不强制换行
![9.gif](https://i.loli.net/2018/10/24/5bd0901f8e4a8.gif)
分析下文档中的这张图，“5” 号和 “6” 号 View 设置 layout_wrapBefore 属性为ture 的时候，不管前面剩余多少空间，都会强制换行

到这里，flexboxLayout 基本属性就介绍完毕了。

然后再来介绍一下跟 recycleView 结合使用。
#### 4.  高能：与 RecyclewView 结合使用
Flexbox 能够作为一个 LayoutManager(FlexboxlayoutManager) 用在 RecyclerView 里面，这也就意味着你可以在一个有大量 Item 的可滚动容器里面使用 Flexbox，提高性能。具体使用示例：

```
        //设置主轴方向为横轴
        FlexboxLayoutManager manager = new FlexboxLayoutManager(this, FlexDirection.ROW);
        //设置item沿主轴方向的位置
        manager.setJustifyContent(JustifyContent.CENTER);
        //设置item 沿次轴方向的位置
        manager.setAlignItems(AlignItems.CENTER);

        recycleView.setLayoutManager(manager);
        centerGridAdapter = new CenterGridAdapter(items, this);
        recycleView.setAdapter(centerGridAdapter);
```
可以看到跟 RecycleView 的其他 manager 使用一样，只需设置 manager 属性即可，属性值为上面叙述的几个容器的属性。
如果想对某个 item 进行单独的设置，可以在 adapter 中去设置，设置示例代码为：

```
 ViewGroup.LayoutParams lp =  holder.itemLL.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp =
                    (FlexboxLayoutManager.LayoutParams)  holder.itemLL.getLayoutParams();
            flexboxLp.setFlexGrow(1.0f);
        }
```
我这里是设置每个 item 有个权重（相当于 Linearlayout 的 weight 属性），所以会按比例分配 item 的宽，而不是我布局中设定的固定宽高。看下效果：
![10.png](https://i.loli.net/2018/10/24/5bd0901cb94cb.png)
