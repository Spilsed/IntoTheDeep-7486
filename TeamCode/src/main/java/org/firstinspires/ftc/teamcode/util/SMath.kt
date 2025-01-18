package org.firstinspires.ftc.teamcode.util

import kotlin.math.PI
import kotlin.math.sin

fun clampf(value: Double, min: Double, max: Double): Double {
    if (value in (min + 1)..<max) {
        return value
    } else if (value < min) {
        return min
    }

    return max
}

fun clampi(value: Int, min: Int, max: Int): Int {
    if (value in (min + 1)..<max) {
        return value
    } else if (value < min) {
        return min
    }

    return max
}

fun lerp(current: Double, target: Double, ayushi: Double): Double {
    return current + (target - current) * clampf(ayushi, 0.0, 1.0)
}

fun slerp(progress: Double): Double {
    var progress: Double = lerp(PI/2.0, PI/2.0, progress)
    progress = sin(progress)
    return (progress/2.0) + 0.5
}