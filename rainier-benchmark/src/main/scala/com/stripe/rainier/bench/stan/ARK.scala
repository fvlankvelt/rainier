package com.stripe.rainier.bench.stan

import com.stripe.rainier.core._

//https://github.com/stan-dev/stat_comp_benchmarks/tree/master/benchmarks/arK
//Stan: Gradient evaluation took 9.7e-05 seconds
//JMH: 3.564 ± 0.011  us/op
class ARK extends ModelBenchmark {
  def model = {
    val alpha = Normal(0, 10).real
    val sigma = Cauchy(0, 2.5).real.abs
    val betas = Normal(0, 10).vec(5)

    5.until(ys.size).foldLeft(Model.empty) { (m, t) =>
      val mu = 1.to(5).foldLeft(alpha) {
        case (mu, k) =>
          mu + betas(k - 1) * ys(t - k)
      }
      Model.observe(ys(t), Normal(mu, sigma)).merge(m)
    }
  }

  val ys: List[Double] = List(0.729385275296256, 0.829763592326138,
    0.783952709087295, 1.02595891054919, 0.969682441192509, 1.08288592175834,
    0.706389146531181, 0.776924397131738, 0.461802947592494, 0.638599756526175,
    0.374234442900993, 0.0854966416340545, -0.126014923296092,
    -0.29341513264683, -0.261730880175093, -0.335922137216444,
    -0.501989576638152, -0.64173995319694, -0.622462952277644,
    -0.850899723800135, -0.867835180697812, -1.01638531442171,
    -1.05589014538759, -0.969481231939393, -1.06725191691958, -0.84309890551111,
    -0.682700614885586, -0.444657348873377, -0.592038709023798,
    -0.51056898146948, -0.4178858435044, -0.173616875144782, -0.107250094020246,
    -0.0355025515905346, 0.34321684590779, 0.384190341371906, 0.496087695010727,
    0.708451160491867, 0.596280661478939, 0.641442415152959, 0.651322273548654,
    0.594215348383588, 0.777941417571685, 0.730776232521844, 0.482247269436388,
    0.532037223409171, 0.485102217466375, 0.368552121947764, 0.353326811282966,
    0.163369000196018, 0.253038940955425, 0.000662142955587017,
    -0.0157674957606101, -0.0144909053918993, -0.0252628993474995,
    -0.00562561275818779, -0.318917733201166, -0.0227791611780233,
    -0.0716865706590353, -0.185018251415619, -0.235007568241763,
    -0.124311187950503, -0.130492356525083, -0.199477772558471,
    0.0775739320050886, -0.162852211064376, 0.0234645663166571,
    -0.0646807914459764, 0.0845324980831258, 0.0550150772764736,
    0.141742407422407, 0.087663822191961, -0.0528467198143955,
    0.0695217091527049, -0.101449997046079, -0.0688806547212088,
    -0.251207322706364, -0.390287282754959, -0.167628361140929,
    -0.279163551699556, -0.312125350629681, -0.286550254685909,
    -0.411770162210457, -0.49820984576771, -0.502852278682366,
    -0.739400448047319, -0.249228092418385, -0.471015885895547,
    -0.464572707570607, -0.445705811708705, -0.284423712071644,
    -0.0227786997826842, -0.0605507195812691, 0.0662256249403969,
    0.140523292748878, 0.276339488205285, 0.377223367072916, 0.610304459776194,
    0.880869469675832, 0.788517426999928, 0.801363239461586, 0.660967819493831,
    0.809766919626932, 0.820507727387276, 0.980756296603693, 0.724038884732822,
    0.931858716590109, 0.850488155137187, 0.480591863826206, 0.508581134199685,
    0.416112609193462, 0.314668551777029, -0.0281539189230603,
    -0.0717016520912123, -0.143920659210904, -0.522366105075654,
    -0.403362110854399, -0.688632934893217, -0.694717057205284,
    -0.649655707682936, -0.992699298061944, -0.935409881682795,
    -0.896986454560753, -0.978152357233651, -1.06684060798515,
    -0.953562142365608, -0.757077368188084, -0.842575543068801,
    -0.697769743062594, -0.316558185831992, -0.387821730048785,
    -0.0671166462283059, -0.0385154998998829, 0.0895646812575233,
    0.145457893512147, 0.185213165355683, 0.395612761657211, 0.685264428400386,
    0.55631078123181, 0.710844528819833, 0.746280468988738, 0.668451928474656,
    0.693653533291797, 0.737999274072457, 0.662770812831593, 0.690093980208194,
    0.680730469397247, 0.373319198885232, 0.453674060090906, 0.313768226011401,
    0.450033431955293, -0.0337947734009151, 0.00473407366632503,
    -0.0856874439244822, 0.108254326965879, -0.139826971992822,
    -0.042973544871492, -0.454163735617593, -0.332642814925886,
    -0.233353410333937, -0.201461260684301, -0.182679242556492,
    -0.227884813666778, -0.0415383584301712, -0.121007767690214,
    -0.0724354047820547, -0.0482025570865206, 0.158014909751014,
    0.0583926960184516, -0.111755943150815, -0.0381725259003516,
    0.178981952872492, -0.0148415779327341, 0.0500966207338629,
    -0.198059211684969, -0.140011401320248, -0.174673463929191,
    -0.176785085296007, -0.215414386368314, -0.414474585019753,
    -0.340543408755025, -0.448279041603314, -0.657115953575154,
    -0.354643305309776, -0.525708117368173, -0.656437230992476,
    -0.524526301701093, -0.475865585094874, -0.363131678586159,
    -0.336691538939934, -0.3254437415468, 0.000669469210704293,
    0.208042241378832, 0.00231649513347475, 0.112475210747327,
    0.349403714630085, 0.471702394545, 0.590660043192963, 0.604863406407812,
    0.933197909938745)
}
