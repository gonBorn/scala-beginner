# Scala Beginner

It's so sad that I execute `rm -rf scala-beginner` on the initial version of this repo in my local env on Feb 17th, 2023.
So I'm going to write down some notes for this language again.

# Reference links
https://www.baeldung.com/scala/cats-intro

# Note

## Semigroupal and Applicative
functor和monads提供了map和flatMap的穿行操作，但是有些操作是他们不能提供的
Examples:
1. 如我们想做表单的校验，我们会希望一次返回所有的error给用户，而不是遇到第一个error就抛出异常, 如果我们用monad Either就会是后者这种效果
1. 如果我们想并行执行很多不相关且运行时间长的task，flatMap/map不能做到这种效果，他们使每一步计算强依赖于上一步

semigroupal包含了组合多个context的概念

## Invariant
在 Scala Cats 中，Invariant 是一个 type class，它表示一种类型构造器（也称为“类型函数”），它接受一个类型参数，并返回一个新类型。
在这种情况下，Invariant 表示的是一种特殊类型构造器，它可以对类型进行双向转换，即将一种类型转换为另一种类型，然后再将其转换回原始类型。

Invariant 提供了两个方法 imap 和 contramap，分别用于双向转换和逆变转换。imap 方法将一个类型转换为另一个类型，然后再将其转换回原始类型，
而 contramap 方法则只能将一个类型转换为另一个类型。这使得开发者可以在不失去类型安全的情况下，对一些数据类型进行转换，从而获得更好的灵活性和代码可重用性。