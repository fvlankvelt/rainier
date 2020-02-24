package com.stripe.rainier.notebook

import com.stripe.rainier.sampler._
import almond.api.JupyterApi

case class HTMLProgress(kernel: JupyterApi, delay: Double) extends Progress {
  val id = java.util.UUID.randomUUID().toString

  val outputEverySeconds = 0.1

  def start(chain: Int, stats: Stats) = {
    val idN = id + "-" + chain
    val chainStr = s"<b>Chain ${chain}</b>"
    kernel.publish.html(chainStr, idN)
  }

  def refresh(chain: Int, stats: Stats) = {
    val idN = id + "-" + chain
    val chainStr = s"<b>Chain ${chain}</b>"
    kernel.publish.updateHtml(chainStr + ": " + render(stats), idN)
  }

  def finish(chain: Int, stats: Stats) = {
    val idN = id + "-" + chain
    val chainStr = s"<b>Chain ${chain} complete</b>"
    kernel.publish.updateHtml(chainStr + ": " + render(stats), idN)
  }

  private def renderTime(nanos: Long): String =
    if (nanos < 1000)
      s"${nanos}ns"
    else if (nanos < 1e6)
      f"${nanos / 1000}us"
    else if (nanos < 1e9)
      f"${nanos / 1000000}ms"
    else {
      val totalSeconds = nanos / 1000000000
      if (totalSeconds < 60)
        totalSeconds.toString + "s"
      else {
        val totalMinutes = totalSeconds / 60
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60
        val seconds = totalSeconds % 60
        f"${hours}%2d:${minutes}%2d:${seconds}%2d"
      }
    }

  private def render(p: Stats): String = {
    val iteration =
      if (p.iterations > 0) {
        val itNum = s"Iteration: ${p.iterations}"
        val itTime = renderTime(p.iterationTimes.mean.toLong)
        s"$itNum ($itTime)"
      } else
        ""

    val stepSize = f"Step size: ${p.stepSizes.mean}%.1g"
    val gradientTime =
      if (p.gradientEvaluations > 0)
        "(" + renderTime(p.gradientTimes.mean.toLong) + ")"
      else ""
    val gradient =
      f"Total gradient evaluations: ${p.gradientEvaluations.toDouble}%.1g $gradientTime"
    val acceptance =
      if (p.iterations > 0)
        f"Acceptance rate: ${p.acceptanceRates.mean}%.2f"
      else ""
    s"<div>$iteration</div> <div>$acceptance</div> <div>$stepSize</div> <div>$gradient</div>"
  }
}