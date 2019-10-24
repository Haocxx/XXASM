# XXASM
### 移除字节码中的冗余字段

## Feature:
### 1.移除所有非静态方法的private可见性修饰
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
