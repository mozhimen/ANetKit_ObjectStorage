package com.mozhimen.netk.objectstorage.cos

import com.tencent.qcloud.core.auth.BasicLifecycleCredentialProvider
import com.tencent.qcloud.core.auth.QCloudLifecycleCredentials
import com.tencent.qcloud.core.auth.SessionQCloudCredentials
import com.tencent.qcloud.core.common.QCloudClientException


/**
 * @ClassName COSSessionCredentialProvider
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/12/21
 * @Version 1.0
 */
class COSSessionCredentialProvider : BasicLifecycleCredentialProvider() {
    @Throws(QCloudClientException::class)
    override fun fetchNewCredentials(): QCloudLifecycleCredentials {

        // 首先从您的临时密钥服务器获取包含了密钥信息的响应

        // 然后解析响应，获取临时密钥信息
        val tmpSecretId = "SECRETID" // 临时密钥 SecretId
        val tmpSecretKey = "SECRETKEY" // 临时密钥 SecretKey
        val sessionToken = "SESSIONTOKEN" // 临时密钥 Token
        val expiredTime = 1556183496L //临时密钥有效截止时间戳，单位是秒

        //建议返回服务器时间作为签名的开始时间，避免由于用户手机本地时间偏差过大导致请求过期
        // 返回服务器时间作为签名的起始时间
        val startTime = 1556182000L //临时密钥有效起始时间，单位是秒

        // 最后返回临时密钥信息对象
        return SessionQCloudCredentials(
            tmpSecretId, tmpSecretKey,
            sessionToken, startTime, expiredTime
        )
    }
}

