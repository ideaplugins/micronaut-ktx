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

```kotlin
val context = buildAndStart<VehiclesFactory>()
val allEngines = context.getBeansOfType<Engine>()
val definitions = context.getBeanDefinitions<Engine>()
val bean = context.getBean<Vehicle>()
val vehicle: Vehicle = context.getBean()
scheduler.scheduleCallable("0 0 12 * * ?") {
    doWork()
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
