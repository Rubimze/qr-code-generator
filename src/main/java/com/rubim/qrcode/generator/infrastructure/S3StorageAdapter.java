package com.rubim.qrcode.generator.infrastructure;

import com.rubim.qrcode.generator.ports.StoragePorts;
import software.amazon.awssdk.services.s3.S3Client;

public class S3StorageAdapter implements StoragePorts {

    private final S3Client s3Client;

    private final String bucketName;

    private final String region;



}
