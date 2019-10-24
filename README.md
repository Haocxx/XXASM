# XXASM
### 移除字节码中冗余字段的Android插件
可通过gradle插件形式引入，通过Transform时期对字节码的处理，删除工程Java文件JavaCompile编译出来甚至引入的jar/aar包中的字节码文件冗余字段，达到缩小APK包体积的目的。

## Configuration:
在app的build.gradle中，XXASM支持如下配置：
```gradle
xxasm {
    ignoreListPath = "$rootDir/property/xxasm/ignore.txt"
    enableRemoveNonStaticPrivateMethodSign = true
    enableRemoveNonStaticPrivateFieldSign = true
}
```
### ignoreListPath:
配置不经过XXASM处理的类名清单列表文件。类似混淆的proguard文件，该项指定的文件中的类将被XXASM忽略不做处理。
#### Example:
```txt
**webview** //所有包含webview字段的类不做处理
**.R //所有以".R"为结尾的类不做处理
com.haocxx.** //所有以"com.haocxx."开头的类不做处理
com.iqiyi.Example //类“com.iqiyi.Example”不做处理
```
### enableRemoveNonStaticPrivateMethodSign:
是否启用处理非静态private方法（Feature 1）的开关。true为开，false为关。默认为开。
### enableRemoveNonStaticPrivateFieldSign:
是否启用处理非静态private全局变量（Feature 2）的开关。true为开，false为关。默认为开。

## Log Output:
XXASM的处理会将如下Log文件输出到build/intermediates/xxasm下
### 1.RemovedSyntheticAccessMethod.txt
被删除的因private方法而生成的access$方法清单
### 2.RemovedSyntheticAccessFieldLog
被删除的因private变量而生成的access$方法清单
### 3.RemovePrivateMethodSign
被移除private可见性修饰符的方法清单
### 4.RemoveFinalMethodSign
被移除final可见性修饰符的方法清单
### 5.ReplaceProtectedMethodSign
protected被替换为public的方法清单
### 6.RemovePrivateSignField
被移除private可见性修饰符的变量清单
### 7.RemoveFinalSignField
被移除final可见性修饰符的变量清单
### 8.ReplaceProtectedSignField
protected被替换为public的变量清单

## Feature:
### 1.移除所有非静态方法的private可见性修饰
#### Java代码：
```Java
private void test(int a, int b) {

}
```
#### 反编译的字节码处理前后对比：
before:
```decompiled-class

.method private test(II)V
.registers 3
.param p1, "a"    # I
.param p2, "b"    # I

.line 49
return-void
.end method


.method static synthetic access$200(Lcom/haocxx/xxasm/MainActivity;II)V
.registers 3
.param p0, "x0"    # Lcom/haocxx/xxasm/MainActivity;
.param p1, "x1"    # I
.param p2, "x2"    # I

.line 9
invoke-direct {p0, p1, p2}, Lcom/haocxx/xxasm/MainActivity;->test(II)V

return-void
.end method

```
after:
```decompiled-class

.method test(II)V
.registers 3
.param p1, "a"    # I
.param p2, "b"    # I

.line 49
return-void
.end method

```
### 2.移除所有非静态全局变量的private可见性修饰
#### Java代码：
```Java
private BaseActivity a = new BaseActivity();
```
#### 反编译的字节码处理前后对比：
before:
```decompiled-class

.field private a:Lcom/haocxx/xxasm/BaseActivity;


.method static synthetic access$000(Lcom/haocxx/xxasm/MainActivity;)Lcom/haocxx/xxasm/BaseActivity;
.registers 2
.param p0, "x0"    # Lcom/haocxx/xxasm/MainActivity;

.line 9
iget-object v0, p0, Lcom/haocxx/xxasm/MainActivity;->a:Lcom/haocxx/xxasm/BaseActivity;

return-object v0
.end method

```
after:
```decompiled-class

.field a:Lcom/haocxx/xxasm/BaseActivity;

```
### 3.移除所有方法和全局变量的final修饰
#### Java代码：
```Java
final int getTAG() {
    return TAG;
}
```
#### 反编译的字节码处理前后对比：
before:
```decompiled-class

.method final getTAG()I
.registers 2

.line 60
const/4 v0, 0x1

return v0
.end method

```
after:
```decompiled-class

.method getTAG()I
.registers 2

.line 60
const/4 v0, 0x1

return v0
.end method

```
### 4.将所有protected方法和全局变量的可见性替换为public
#### Java代码：
```Java
protected BaseActivity get() {
    return new BaseActivity();
}
```
#### 反编译的字节码处理前后对比：
before:
```decompiled-class

.method protected get()Lcom/haocxx/xxasm/BaseActivity;
.registers 2

.line 56
new-instance v0, Lcom/haocxx/xxasm/BaseActivity;

invoke-direct {v0}, Lcom/haocxx/xxasm/BaseActivity;-><init>()V

return-object v0
.end method

```
after:
```decompiled-class

.method public get()Lcom/haocxx/xxasm/BaseActivity;
.registers 2

.line 56
new-instance v0, Lcom/haocxx/xxasm/BaseActivity;

invoke-direct {v0}, Lcom/haocxx/xxasm/BaseActivity;-><init>()V

return-object v0
.end method

```
