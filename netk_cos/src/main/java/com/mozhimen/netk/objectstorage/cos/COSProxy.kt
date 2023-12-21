package com.mozhimen.netk.objectstorage.cos

import com.tencent.qcloud.core.auth.QCloudCredentialProvider
import com.tencent.qcloud.core.auth.ShortTimeCredentialProvider







/**
 * @ClassName COSProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/12/21
 * @Version 1.0
 */
class COSProxy {
    var myCredentialProvider: QCloudCredentialProvider = COSSessionCredentialProvider()

//    var secretId = "SECRETID" //永久密钥 secretId
//    var secretKey = "SECRETKEY" //永久密钥 secretKey
//
//
//    // keyDuration 为请求中的密钥有效期，单位为秒
//    var myCredentialProvider: QCloudCredentialProvider = ShortTimeCredentialProvider(secretId, secretKey, 300)

}