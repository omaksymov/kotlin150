# kotlin150
Showcase of Kotlin 1.5.0 IR issue (ref. https://youtrack.jetbrains.com/issue/KT-46503)

### Issue info
Build is failing with `org.jetbrains.kotlin.backend.common.BackendException: Backend Internal error: Exception during IR lowering` error.
Specific method where it is failing is [this](https://github.com/omaksymov/kotlin150/blob/main/app/src/main/java/com/omaks/sample/kotlin150/Store.kt#L36) - 
commenting out this method does not cause build failure.


More detailed stacktrace:
```
File being compiled: .../Store.kt
The root cause java.lang.AssertionError was thrown at: org.jetbrains.kotlin.ir.overrides.IrOverridingUtilKt.buildFakeOverrideMember(IrOverridingUtil.kt:61)
	at org.jetbrains.kotlin.backend.common.CodegenUtil.reportBackendException(CodegenUtil.kt:239)
	at org.jetbrains.kotlin.backend.common.CodegenUtil.reportBackendException$default(CodegenUtil.kt:235)
	at org.jetbrains.kotlin.backend.common.phaser.PerformByIrFilePhase.invoke(PhaseBuilders.kt:124)
	at org.jetbrains.kotlin.backend.common.phaser.PerformByIrFilePhase.invoke(PhaseBuilders.kt:112)
	at org.jetbrains.kotlin.backend.common.phaser.NamedCompilerPhase.invoke(CompilerPhase.kt:94)
	at org.jetbrains.kotlin.backend.common.phaser.CompositePhase.invoke(PhaseBuilders.kt:30)
	at org.jetbrains.kotlin.backend.common.phaser.NamedCompilerPhase.invoke(CompilerPhase.kt:94)
	at org.jetbrains.kotlin.backend.common.phaser.CompilerPhaseKt.invokeToplevel(CompilerPhase.kt:41)
	at org.jetbrains.kotlin.backend.jvm.JvmLower.lower(JvmLower.kt:406)
	at org.jetbrains.kotlin.backend.jvm.JvmIrCodegenFactory.doGenerateFilesInternal(JvmIrCodegenFactory.kt:191)
	at org.jetbrains.kotlin.backend.jvm.JvmIrCodegenFactory.generateModule(JvmIrCodegenFactory.kt:62)
	at org.jetbrains.kotlin.codegen.KotlinCodegenFacade.compileCorrectFiles(KotlinCodegenFacade.java:35)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.generate(KotlinToJVMBytecodeCompiler.kt:592)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli(KotlinToJVMBytecodeCompiler.kt:212)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli$default(KotlinToJVMBytecodeCompiler.kt:155)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:169)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:52)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:88)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:44)
	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:98)
	at org.jetbrains.kotlin.incremental.IncrementalJvmCompilerRunner.runCompiler(IncrementalJvmCompilerRunner.kt:386)
	at org.jetbrains.kotlin.incremental.IncrementalJvmCompilerRunner.runCompiler(IncrementalJvmCompilerRunner.kt:110)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.compileIncrementally(IncrementalCompilerRunner.kt:303)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.compileImpl(IncrementalCompilerRunner.kt:121)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.compile(IncrementalCompilerRunner.kt:74)
	at org.jetbrains.kotlin.daemon.CompileServiceImplBase.execIncrementalCompiler(CompileServiceImpl.kt:607)
	at org.jetbrains.kotlin.daemon.CompileServiceImplBase.access$execIncrementalCompiler(CompileServiceImpl.kt:96)
	at org.jetbrains.kotlin.daemon.CompileServiceImpl.compile(CompileServiceImpl.kt:1659)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:357)
	at sun.rmi.transport.Transport$1.run(Transport.java:200)
	at sun.rmi.transport.Transport$1.run(Transport.java:197)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.Transport.serviceCall(Transport.java:196)
	at sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:573)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:834)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:688)
	at java.security.AccessController.doPrivileged(Native Method)
	at sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:687)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.AssertionError: Unexpected variance in super type argument: out @1
	at org.jetbrains.kotlin.ir.overrides.IrOverridingUtilKt.buildFakeOverrideMember(IrOverridingUtil.kt:61)
	at org.jetbrains.kotlin.backend.jvm.lower.indy.LambdaMetafactoryArgumentsBuilder.getLambdaMetafactoryArgsOrNullInner(LambdaMetafactoryArguments.kt:145)
	at org.jetbrains.kotlin.backend.jvm.lower.indy.LambdaMetafactoryArgumentsBuilder.getLambdaMetafactoryArgumentsOrNull(LambdaMetafactoryArguments.kt:111)
	at org.jetbrains.kotlin.backend.jvm.lower.FunctionReferenceLowering.visitTypeOperator(FunctionReferenceLowering.kt:179)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitTypeOperator(IrElementTransformerVoid.kt:245)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitTypeOperator(IrElementTransformerVoid.kt:24)
	at org.jetbrains.kotlin.ir.expressions.impl.IrTypeOperatorCallImpl.accept(IrTypeOperatorCallImpl.kt:40)
	at org.jetbrains.kotlin.ir.expressions.IrExpression.transform(IrExpression.kt:33)
	at org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression.transformChildren(IrFunctionAccessExpression.kt:48)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitExpression(IrElementTransformerVoid.kt:131)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitMemberAccess(IrElementTransformerVoid.kt:192)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitFunctionAccess(IrElementTransformerVoid.kt:195)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitCall(IrElementTransformerVoid.kt:198)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitCall(IrElementTransformerVoid.kt:199)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitCall(IrElementTransformerVoid.kt:24)
	at org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl.accept(IrCallImpl.kt:47)
	at org.jetbrains.kotlin.ir.expressions.IrExpression.transform(IrExpression.kt:33)
	at org.jetbrains.kotlin.ir.expressions.impl.IrTypeOperatorCallImpl.transformChildren(IrTypeOperatorCallImpl.kt:47)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitExpression(IrElementTransformerVoid.kt:131)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitTypeOperator(IrElementTransformerVoid.kt:244)
	at org.jetbrains.kotlin.backend.jvm.lower.FunctionReferenceLowering.visitTypeOperator(FunctionReferenceLowering.kt:151)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitTypeOperator(IrElementTransformerVoid.kt:245)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitTypeOperator(IrElementTransformerVoid.kt:24)
	at org.jetbrains.kotlin.ir.expressions.impl.IrTypeOperatorCallImpl.accept(IrTypeOperatorCallImpl.kt:40)
	at org.jetbrains.kotlin.ir.expressions.IrExpression.transform(IrExpression.kt:33)
	at org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl.transformChildren(IrReturnImpl.kt:41)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitExpression(IrElementTransformerVoid.kt:131)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitReturn(IrElementTransformerVoid.kt:292)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitReturn(IrElementTransformerVoid.kt:293)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitReturn(IrElementTransformerVoid.kt:24)
	at org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl.accept(IrReturnImpl.kt:34)
	at org.jetbrains.kotlin.ir.expressions.IrExpression.transform(IrExpression.kt:33)
	at org.jetbrains.kotlin.ir.expressions.IrExpression.transform(IrExpression.kt:26)
	at org.jetbrains.kotlin.ir.expressions.IrBlockBody.transformChildren(IrBody.kt:62)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitBody(IrElementTransformerVoid.kt:108)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitBlockBody(IrElementTransformerVoid.kt:117)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitBlockBody(IrElementTransformerVoid.kt:118)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitBlockBody(IrElementTransformerVoid.kt:24)
	at org.jetbrains.kotlin.ir.expressions.IrBlockBody.accept(IrBody.kt:54)
	at org.jetbrains.kotlin.ir.expressions.IrBody.transform(IrBody.kt:27)
	at org.jetbrains.kotlin.ir.declarations.IrFunction.transformChildren(IrFunction.kt:69)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitDeclaration(IrElementTransformerVoid.kt:57)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitFunction(IrElementTransformerVoid.kt:69)
	at org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext.visitFunctionNew(IrElementTransformerVoidWithContext.kt:115)
	at org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext.visitFunction(IrElementTransformerVoidWithContext.kt:68)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitSimpleFunction(IrElementTransformerVoid.kt:72)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitSimpleFunction(IrElementTransformerVoid.kt:73)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitSimpleFunction(IrElementTransformerVoid.kt:24)
	at org.jetbrains.kotlin.ir.declarations.IrSimpleFunction.accept(IrSimpleFunction.kt:29)
	at org.jetbrains.kotlin.ir.IrElement$DefaultImpls.transform(IrElement.kt:32)
	at org.jetbrains.kotlin.ir.IrElementBase.transform(IrElementBase.kt:19)
	at org.jetbrains.kotlin.ir.util.TransformKt.transformInPlace(transform.kt:35)
	at org.jetbrains.kotlin.ir.declarations.IrClass.transformChildren(IrClass.kt:67)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitDeclaration(IrElementTransformerVoid.kt:57)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitClass(IrElementTransformerVoid.kt:66)
	at org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext.visitClassNew(IrElementTransformerVoidWithContext.kt:111)
	at org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext.visitClass(IrElementTransformerVoidWithContext.kt:47)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitClass(IrElementTransformerVoid.kt:67)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoid.visitClass(IrElementTransformerVoid.kt:24)
	at org.jetbrains.kotlin.ir.declarations.IrClass.accept(IrClass.kt:56)
	at org.jetbrains.kotlin.ir.IrElement$DefaultImpls.transform(IrElement.kt:32)
	at org.jetbrains.kotlin.ir.IrElementBase.transform(IrElementBase.kt:19)
	at org.jetbrains.kotlin.ir.declarations.impl.IrFileImpl.transformChildren(IrFileImpl.kt:71)
	at org.jetbrains.kotlin.ir.visitors.IrElementTransformerVoidKt.transformChildrenVoid(IrElementTransformerVoid.kt:330)
	at org.jetbrains.kotlin.backend.jvm.lower.FunctionReferenceLowering.lower(FunctionReferenceLowering.kt:79)
	at org.jetbrains.kotlin.backend.common.phaser.FileLoweringPhaseAdapter.invoke(PhaseBuilders.kt:155)
	at org.jetbrains.kotlin.backend.common.phaser.FileLoweringPhaseAdapter.invoke(PhaseBuilders.kt:151)
	at org.jetbrains.kotlin.backend.common.phaser.NamedCompilerPhase.invoke(CompilerPhase.kt:94)
	at org.jetbrains.kotlin.backend.common.phaser.PerformByIrFilePhase.invoke(PhaseBuilders.kt:121)
	... 42 more
```

### Workarounds
Downgrading to Kotlin 1.4.32 fixes the issue.
