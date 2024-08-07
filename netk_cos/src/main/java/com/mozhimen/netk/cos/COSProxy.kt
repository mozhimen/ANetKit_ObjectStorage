package com.mozhimen.netk.cos

import com.mozhimen.basick.utilk.bases.BaseUtilK
import com.mozhimen.basick.utilk.java.text.UtilKSimpleDateFormat
import com.mozhimen.basick.utilk.java.util.date2longDate
import com.mozhimen.netk.cos.commons.IFetchCredentialsListener
import com.mozhimen.netk.cos.impls.COSLifecycleCredentialProvider
import com.mozhimen.netk.cos.impls.COSStaticCredentialProvider
import com.mozhimen.netk.os.basic.commons.IOSProxy
import com.tencent.cos.xml.CosXmlService
import com.tencent.cos.xml.CosXmlServiceConfig
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


/**
 * @ClassName COSProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/12/21
 * @Version 1.0
 */
class COSProxy : BaseUtilK(), IOSProxy {
    private var _cosXmlService: CosXmlService? = null
    private val _simpleDateFormat: SimpleDateFormat by lazy {
        UtilKSimpleDateFormat.get("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()).apply { timeZone = TimeZone.getTimeZone("UTC") }
    }

    private var _cOSLifecycleCredentialProvider: COSLifecycleCredentialProvider? = null
    private var _cOSStaticCredentialProvider: COSStaticCredentialProvider? = null

    //////////////////////////////////////////////////////////////////////////

    override fun strTimeExpiration2longTimeExpiration(strDate: String): Long? =
        try {
            _simpleDateFormat.parse(strDate)?.date2longDate()
        } catch (e: Exception) {
            null
        }

    fun getCOSServerCredentialProvider(): COSLifecycleCredentialProvider? =
        _cOSLifecycleCredentialProvider

    fun getCOSStaticCredentialProvider(): COSStaticCredentialProvider? =
        _cOSStaticCredentialProvider

    fun getService(): CosXmlService? =
        _cosXmlService

    fun initService(region: String, iFetchCredentialsListener: IFetchCredentialsListener): CosXmlService {
        // 存储桶region可以在COS控制台指定存储桶的概览页查看 https://console.cloud.tencent.com/cos5/bucket/ ，关于地域的详情见 https://cloud.tencent.com/document/product/436/6224
        //val region = "ap-guangzhou"
        val serviceConfig = CosXmlServiceConfig.Builder()
            .setRegion(region)
            .isHttps(true) // 使用 HTTPS 请求，默认为 HTTP 请求
            .builder()
        val cosLifecycleCredentialProvider = COSLifecycleCredentialProvider(iFetchCredentialsListener)
        return CosXmlService(_context, serviceConfig, cosLifecycleCredentialProvider).also {
            _cosXmlService = it
            _cOSLifecycleCredentialProvider = cosLifecycleCredentialProvider
        }
    }

    fun initService_ofStatic(region: String): CosXmlService {
        // 存储桶region可以在COS控制台指定存储桶的概览页查看 https://console.cloud.tencent.com/cos5/bucket/ ，关于地域的详情见 https://cloud.tencent.com/document/product/436/6224
        //val region = "ap-guangzhou"
        val serviceConfig = CosXmlServiceConfig.Builder()
            .setRegion(region)
            .isHttps(true) // 使用 HTTPS 请求，默认为 HTTP 请求
            .builder()
        val cosLifecycleCredentialProvider = COSStaticCredentialProvider()
        return CosXmlService(_context, serviceConfig, cosLifecycleCredentialProvider).also {
            _cosXmlService = it
            _cOSStaticCredentialProvider = cosLifecycleCredentialProvider
        }
    }

    //////////////////////////////////////////////////////////////////////////

//    private fun initService() {
//        // 存储桶region可以在COS控制台指定存储桶的概览页查看 https://console.cloud.tencent.com/cos5/bucket/ ，关于地域的详情见 https://cloud.tencent.com/document/product/436/6224
//        val region = "ap-guangzhou"
//        val serviceConfig = CosXmlServiceConfig.Builder()
//            .setRegion(region)
//            .isHttps(true) // 使用 HTTPS 请求，默认为 HTTP 请求
//            .builder()
//        context = InstrumentationRegistry.getInstrumentation().getTargetContext()
//        cosXmlService = CosXmlService(
//            context, serviceConfig,
//            ServerCredentialProvider()
//        )
//    }
//
//    @Test
//    fun testObjectPresignUrl() {
//        initService()
//
//        // 获取预签名下载链接
//        getPresignDownloadUrl()
//
//        // 获取预签名上传链接
//        getPresignUploadUrl()
//        // .cssg-methods-pragma
//    }
}