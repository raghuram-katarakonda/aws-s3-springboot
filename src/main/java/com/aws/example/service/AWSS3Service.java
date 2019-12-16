package com.aws.example.service;

import java.io.File;
import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

public interface AWSS3Service {

	public boolean doesBucketExist(String bucketName);

	public Bucket createBucket(String bucketName);

	public List<Bucket> listBuckets();

	public void deleteBucket(String bucketName);

	public PutObjectResult putObject(String bucketName, String key, File file);

	public ObjectListing listObjects(String bucketName);

	public S3Object getObject(String bucketName, String objectKey);

	public CopyObjectResult copyObject(String sourceBucketName, String sourceKey, String destinationBucketName,
			String destinationKey);

	public void deleteObject(String bucketName, String objectKey);

	public DeleteObjectsResult deleteObjects(DeleteObjectsRequest delObjReq);

}
