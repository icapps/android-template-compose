import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.register

fun Project.registerKtLintTasks() {
    val ktlint = configurations.create("ktlint")

    dependencies {
        ktlint("com.pinterest:ktlint:0.50.0")
    }

    tasks.register<JavaExec>("ktlLintCheck") {
        group = "verification"
        description = "Check Kotlin code style."
        mainClass.set("com.pinterest.ktlint.Main")
        jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
        classpath = ktlint
        args(
            "--reporter=plain",
            "--reporter=checkstyle,output=${buildDir}/reports/ktlint/ktlintMainSourceSetCheck.txt",
            "src/**/*.kt",
            "!src/test/**/*Test.kt" //ignore test files
        )
    }

    tasks.register<JavaExec>("ktLintFormat") {
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        mainClass.set("com.pinterest.ktlint.Main")
        jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
        classpath = ktlint
        args("-F", "src/**/*.kt")
    }
}
