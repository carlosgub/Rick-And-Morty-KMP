package org.carlosgub.yt.rick.and.morty

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.carlosgub.yt.rick.and.morty.di.initKoin
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@RunWith(ParameterizedRobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(
    sdk = [33],
    qualifiers = RobolectricDeviceQualifiers.Pixel4a,
    application = TestApplication::class
)
class PreviewScreenshotTest(
    private val preview: ComposablePreview<AndroidPreviewInfo>,
) : KoinTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @BeforeTest
    fun setup() {
        if (org.koin.core.context.GlobalContext.getOrNull() == null) {
            initKoin()
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun captureScreenshot() {
        composeTestRule.setContent {
            preview()
        }
        val fileName = preview
            .toString()
            .replace(" ", "_")
            .replace("(", "[")
            .replace(")", "]")
            .replace(".", "_")
        composeTestRule.onRoot().captureRoboImage("screenshots/$fileName.png")
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun values(): List<ComposablePreview<AndroidPreviewInfo>> =
            AndroidComposablePreviewScanner()
                .scanPackageTrees("org.carlosgub.yt.rick.and.morty")
                .includePrivatePreviews()
                .getPreviews()
    }
}
