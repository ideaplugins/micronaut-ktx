# Micronaut kotlin extensions

[![Build Status](https://travis-ci.org/ideaplugins/micronaut-ktx.svg?branch=master)](https://travis-ci.org/ideaplugins/micronaut-ktx)
[![Bintray](https://img.shields.io/bintray/v/agomez/maven/micronaut-ktx.svg?maxAge=2592000)](https://bintray.com/agomez/maven/micronaut-ktx)
[![Download](https://api.bintray.com/packages/agomez/maven/micronaut-ktx/images/download.svg)](https://bintray.com/agomez/maven/micronaut-ktx/_latestVersion)
[![Coverage Status](https://coveralls.io/repos/github/ideaplugins/micronaut-ktx/badge.svg?branch=master)](https://coveralls.io/github/ideaplugins/micronaut-ktx?branch=master)
[![Apache 2.0](https://img.shields.io/github/license/ideaplugins/micronaut-ktx.svg)](http://www.apache.org/licenses/LICENSE-2.0)

## Usage

### Maven

Add the repository

```xml
<repository>
    <url>https://dl.bintray.com/agomez/maven</url>
</repository>
```

Add the dependency

```xml
<dependency>
  <groupId>ar.com.agomez</groupId>
  <artifactId>micronaut-ktx</artifactId>
  <version>x.y.z</version>
</dependency>
```

### Gradle (groovy version)

Add the repository

```groovy
maven {
    url = 'https://dl.bintray.com/agomez/maven'
}
```

Add the dependency

```groovy
implementation 'ar.com.agomez:micronaut-ktx:x.y.z'
```

### Gradle (kotlin version)

Add the repository

```kotlin
maven("https://dl.bintray.com/agomez/maven")
```

Add the dependency

```kotlin
implementation("ar.com.agomez:micronaut-ktx:x.y.z")
```

## Example

### ApplicationContext extensions

```kotlin
run<FooApplication>()
run<FooApplication>("env1", "env2")
run<FooApplication>(props, "env1", "env2")
run<FooApplication>(propertySource, "env1", "env2")
buildAndStart<FooApplication>()
buildAndStart<FooApplication>("env1", "env2")
buildAndStart<FooApplication>(propSrc1, propSrc2)
buildAndStart<FooApplication>(props, "env1", "env2")
buildAndStart<FooApplication>(propSrc, "env1", "env2")
```

### BeanContext extensions

```kotlin
val foo = context.createBean<Foo>()
val foo: Foo = context.createBean()
val foo = context.createBean<Foo>(args)
val foo: Foo = context.createBean(args)
val foo = context.createBean<Foo>(argsMap)
val foo: Foo = context.createBean(argsMap)
val foo = context.createStereotypedBean<Foo, Prototype>()
val foo = context.createStereotypedBean<Foo, Prototype>(args)
val foo = context.createStereotypedBean<Foo, Prototype>(argsMap)
val foo = context.destroyBean<Foo>()
val foo: Foo = context.destroyBean()
```

### BeanDefinitionRegistry extensions

```kotlin
val foo = registry.getStereotypedBeanDefinition<Foo, Prototype>()
registry.containsBean<Foo>()
Foo::class in registry
registry.containsStereotypedBean<Foo, Prototype>()
(Foo::class to Prototype::class) in registry
val fooDefinition = registry.findStereotypedBeanDefinition<Foo, Prototype>()
val fooDefinitions = registry.getBeanDefinitions<Foo>()
val fooDefinitions: Collections<BeanDefinition<Foo>> = registry.getBeanDefinitions()
val prototypeDefinitions = registry.getStereotypedBeanDefinitions<Prototype>()
val prototypeRegistrations = registry.getStereotypedBeanRegistrations<Prototype>()
val fooRegistrations = registry.getBeanRegistrations<Foo>()
val fooRegistrations: Collection<BeanRegistration<Foo>> = registry.getBeanRegistrations()
val fooDefinition = registry.findProxiedBeanDefinition<Foo, Prototype>()
val fooDefinition = registry.getProxiedBeanDefinition<Foo, Prototype>()
val fooDefinition = registry.getBeanDefinition<Foo>()
val fooDefinition: BeanDefinition<Foo> = registry.getBeanDefinition()
val fooDefinition = registry.findBeanDefinition<Foo>()
val fooDefinition: BeanDefinition<Foo>? = registry.findBeanDefinition()
registry.registerStereotypedSingleton<Foo, Prototype>(foo, true)
registry.registerStereotypedSingleton<Foo, Prototype>(foo)
registry.registerNotStereotypedSingleton<Foo>(foo)
```

### BeanLocator extensions

```kotlin
val foo = locator.getBean<Foo>()
val foo: Foo = locator.getBean()
val foo = locator.getStereotypedBean<Foo, Prototype>()
val foo = locator.findBean<Foo>()
val foo: Foo? = locator.findBean()
val foo = locator.findStereotypedBean<Foo, Prototype>()
val foos = locator.getBeansOfType<Foo>()
val foos: Collection<Foo> = locator.getBeansOfType()
val prototypeFoos = locator.getStereotypedBeansOfType<Foo, Prototype>()
val foosStream = locator.streamOfType<Foo>()
val foosStream: Stream<Foo> = locator.streamOfType()
val foosSequence = locator.sequenceOfType<Foo>()
val foosSequence: Sequence<Foo> = locator.sequenceOfType()
val prototypeFoosStream = locator.streamOfStereotypedType<Foo, Prototype>()
val prototypeFoosSequence = locator.sequenceOfStereotypedType<Foo, Prototype>()
val foo = locator.getProxyTargetBean<Foo, Prototype>()
val foo = locator.findOrInstantiateBean<Foo>()
val foo: Foo = locator.findOrInstantiateBean()
```

### Micronaut extensions

```kotlin
mnRun<FooApplication>(args)
```

### Qualifier extensions

```kotlin
qualifier.reduce(candidates)
qualifier.qualify(candidates)
```

### Qualifiers extensions

```kotlin
qualifierByAnnotation<Foo, Bar>(metadata)
qualifierByStereotype<Foo, Prototype>()
```

### TaskScheduler extensions

```kotlin
scheduler.scheduleCallable("0 0 12 * * ?") {
    doWork()
}
scheduler.scheduleCallable(Duration.ofSeconds(5)) {
    doMoreWork()
}
```

For more examples check the API documentation and the tests.

## License

Micronaut kotlin extensions is released under the [Apache 2.0 license](LICENSE).

```
Copyright 2018-present by the authors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at following link.

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
