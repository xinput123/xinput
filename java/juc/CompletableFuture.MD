### 1、构建异步任务

|方法|有无返回值|描述|
|:---|:---|:---|
| runAsync | 无 | 进行数据处理，接收前一步骤传递的数据，无返回值。 |
| supplyAsync | 有 | 进行数据处理，接收前一步骤传递的数据，处理加工后返回。返回数据类型可以和前一步骤返回的数据类型不同。 |

### 2、组合 thenCombine / thenAcceptBoth / runAfterBoth

#### 相同点

- 将两个CompletableFuture组合起来， 只有这两个都正常执行完了，才会执行某个任务.

#### 不同点

- thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
- thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
- runAfterBoth 不会把执行结果当做方法入参 ，且没有返回值。

### 3、在两个完成的阶段其中之一上应用函数 applyToEither、acceptEither、runAfterEither

- applyToEither：会将已经执行完成的任务，作为方法入参，传递到指定方法中，且有返回值
- acceptEither: 会将已经执行完成的任务，作为方法入参，传递到指定方法中，且无返回值
- runAfterEither：不会把执行结果当做方法入参，且没有返回值。

