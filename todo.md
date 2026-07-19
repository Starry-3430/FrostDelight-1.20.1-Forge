如果从**原版 Minecraft 可获取材料**开始开发，这是非常合理的路线。第一版（Alpha）建议不要引入大量新矿物、新植物，而是利用原版已有资源建立一个完整的“冰淇淋生产链”。

下面按照 **原料 → 中间材料 → 成品 → 高级产品** 设计，并给重要新增物品标注英文注册名建议（适合 Forge/NeoForge 开发）。

---

# Frozen Delights Alpha 版本内容规划

## 一、基础新增材料（由原版材料加工）

这些是模组核心材料。

---

# 1. 糖浆 Sugar Syrup

英文名：

```
sugar_syrup
```

获取：

### 配方

```
糖 Sugar
+
水瓶 Water Bottle

↓

糖浆 Sugar Syrup
```

用途：

* 所有甜品基础材料
* 增加甜味标签

---

# 2. 奶油 Cream

英文名：

```
cream
```

获取：

```
牛奶 Milk Bucket
+
糖浆 Sugar Syrup

↓

奶油 Cream
```

用途：

冰淇淋核心材料。

---

# 3. 黄油 Butter

英文名：

```
butter
```

获取：

```
牛奶桶 Milk Bucket

↓

离心/加工

↓

黄油 Butter
```

用途：

* 高级冰淇淋
* 烘焙拓展

---

# 4. 香草 Vanilla

英文名：

```
vanilla
```

第一版可以不用自然生成。

获取：

```
甘蔗 Sugar Cane
+
糖

↓

香草模拟材料
```

或者后期增加香草植物。

用途：

香草冰淇淋。

---

# 5. 碎冰 Ice Shard

英文名：

```
ice_shard
```

获取：

```
冰 Ice

↓

粉碎

↓

碎冰 Ice Shard
```

用途：

* 冰棒
* 刨冰
* 冷冻机器

---

# 6. 水果泥 Fruit Puree

英文名：

```
fruit_puree
```

这是一个通用材料。

获取：

例如：

苹果：

```
苹果 Apple

↓

水果泥 Fruit Puree
```

NBT记录水果类型：

例如：

```
fruit=apple
```

优势：

不用制作几十个材料。

---

# 二、基础机器

---

# 1. 冰淇淋机

英文：

```
ice_cream_machine
```

定位：

核心机器。

输入：

```
牛奶
奶油
糖
水果泥
巧克力
```

输出：

```
冰淇淋浆
```

---

GUI:

```
-----------------
| Milk | Sugar |
| Fruit| Cream |
-----------------

      ↓

 Ice Cream Base

-----------------
```

---

# 2. 冷冻机

英文：

```
freezing_machine
```

作用：

把液态产品冻结。

输入：

```
冰淇淋浆
冰棒浆
```

输出：

```
冰淇淋
冰棒
```

---

# 3. 搅拌机

英文：

```
mixer
```

用途：

制作：

* 奶油
* 糖浆
* 果泥

---

# 三、容器类物品

---

# 1. 冰淇淋碗

英文：

```
ice_cream_bowl
```

特点：

独立物品。

不是直接生成冰淇淋。

流程：

```
空碗

↓

加入冰淇淋

↓

冰淇淋碗
```

---

# 2. 冰棒棍

英文：

```
popsicle_stick
```

材料：

原版：

木棍。

也可以：

```
木板

↓

冰棒棍
```

---

# 3. 冰棒模具

英文：

```
popsicle_mold
```

用途：

制作冰棒。

---

# 四、冰棒系统

## 普通冰棒

统一英文格式：

```
xxx_popsicle
```

---

## 1. 牛奶冰棒

Milk Popsicle

```
milk_popsicle
```

材料：

```
牛奶
糖浆
冰棒棍
```

效果：

饥饿：

+4

口渴：

+3

---

## 2. 苹果冰棒

Apple Popsicle

```
apple_popsicle
```

材料：

```
苹果
糖浆
冰棒棍
```

效果：

速度Ⅰ

10秒

---

## 3. 西瓜冰棒

Watermelon Popsicle

```
watermelon_popsicle
```

材料：

```
西瓜片
糖浆
冰棒棍
```

效果：

口渴恢复增加。

---

## 4. 甜浆果冰棒

Sweet Berry Popsicle

```
sweet_berry_popsicle
```

效果：

速度Ⅰ

---

## 5. 蜂蜜冰棒

Honey Popsicle

```
honey_popsicle
```

材料：

```
蜂蜜瓶
```

效果：

生命恢复Ⅰ

---

# 五、冰淇淋系统

统一：

```
xxx_ice_cream
```

---

# 1. 原味冰淇淋

Vanilla Ice Cream

```
vanilla_ice_cream
```

材料：

```
奶油
糖浆
```

恢复：

饥饿：

+6

饱食：

+8

---

# 2. 巧克力冰淇淋

Chocolate Ice Cream

```
chocolate_ice_cream
```

材料：

```
可可豆 Cocoa Beans
+
奶油
```

效果：

力量Ⅰ

10秒

---

# 3. 草莓冰淇淋

Strawberry Ice Cream

```
sweet_berry_ice_cream
```

材料：

```
甜浆果
+
奶油
```

效果：

生命恢复Ⅰ

---

# 4. 西瓜冰淇淋

Watermelon Ice Cream

```
watermelon_ice_cream
```

效果：

口渴恢复高。

---

# 5. 蜂蜜冰淇淋

Honey Ice Cream

```
honey_ice_cream
```

效果：

抗性提升Ⅰ

15秒

---

# 六、可放置冰淇淋方块

这是模组特色。

---

## 冰淇淋球

英文：

```
ice_cream_scoop
```

Block。

类似：

蛋糕。

状态：

```
layers=1-8
```

例如：

```
minecraft:cake
```

一样。

---

支持：

右键：

减少一层。

---

类型：

```
vanilla_scoop
chocolate_scoop
berry_scoop
```

---

# 七、第一版建议加入的物品数量

Alpha：

| 类型   | 数量 |
| ---- | -: |
| 基础材料 | 10 |
| 机器   |  4 |
| 容器   |  3 |
| 冰棒   | 15 |
| 冰淇淋  | 15 |
| 放置方块 | 10 |

约：

**50~60个新增内容**

规模适中。

---

# 八、第一版完整生产链

最终玩家体验：

```
甘蔗
 |
糖
 |
糖浆
 |
 + 牛奶
 |
奶油
 |
 + 苹果/甜浆果/可可豆
 |
冰淇淋机
 |
冰淇淋浆
 |
冷冻机
 |
冰淇淋
 |
食用/摆放
```

---

# 九、建议第一阶段暂不加入

为了控制开发量，以下内容放到 Beta：

❌ 新植物（香草、草莓）

❌ 新矿物

❌ 电力系统

❌ 复杂自动化

❌ 村民职业

❌ 大型工厂

❌ 自定义维度

---

这样设计后，第一版完全可以做到：

* 100% 原版材料可制作
* 不破坏原版平衡
* 有明确科技路线
* 后续方便扩展水果、机器、自动化

而且代码结构上也容易维护：
**Ingredient（原料）→ Processing（机器）→ Food（食物）→ Effect（效果）→ Placement（方块化）**，后续加入新口味只需要添加数据文件即可。
