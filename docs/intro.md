---
id: intro
title: Introduction to Rainier
---

![](/img/rainier-large.jpg)

## Who Rainier is for

Rainier is for building and sampling from Bayesian statistical models. Specifically, it's for building generative models with fixed structure, continuous parameters, and data that can comfortably fit in memory. Generalized linear models (GLMs), for example, are a very common and flexible class of models that fit that description.

This documentation assumes you have at least some basic familiarity with Bayesian modeling and with GLMs. If you don't, the single best text on that subject is McElreath's [Statistical Rethinking](https://xcelab.net/rm/statistical-rethinking/), and we highly recommend reading it.

We also assume you are familiar with Scala. Perhaps this goes without saying, but: Rainier is a Scala library, and letting you build and run your models in Scala, on the JVM, is one of Rainier's distinguishing features.

## Getting Rainier

To add Rainier to your project include the following in your `build.sbt`:

```scala
libraryDependencies += "com.stripe" % "rainier-core" % "@VERSION@"
```

Or, in Ammonite or Jupyter, import it like this:

```scala
import $ivy.`com.stripe:rainier-core:@VERSION@`
```

Then import `com.stripe.rainier.core` to get started.

```scala
import com.stripe.rainier.core._
```

## This Overview

The rest of this overview is split into four sections. We recommend you read them all before you start working with Rainier. They are:

* [Priors and Random Variables](priors.md)

This introduces the `Distribution` and `Real` types and shows you how to construct random variables from prior distributions.

* [Likelihoods and Observations](likelihoods.md)

This introduces the `Model` and `Trace` types, shows how to condition a model on observations, and how to check your sampling diagnostics.

* [Vectors and Covariates](vectors.md)

This introduces the `Vec` and `Fn` types, shows how to deal with larger numbers of parameters and observations.

* [Posteriors and Predictions](posteriors.md)

This introduces the `Generator` type, and shows how to make posterior predictions from a sampled trace.
