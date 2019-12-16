package com.aws.example;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.aws.example.serviceimpl.AWSS3ServiceImpl;

@SpringBootApplication
public class AwsS3PracticeApplication {
	
	 private static final AWSCredentials credentials;
	 private static String bucketName;
	    

	    static {
	        //put your accesskey and secretkey here
	        credentials = new BasicAWSCredentials(
	          "<<your accesskey>>", 
	          "<<your secretkey>>"
	        );
	    }

	public static void main(String[] args) {
		SpringApplication.run(AwsS3PracticeApplication.class, args);
		
        //set-up the client
        AmazonS3 s3client = AmazonS3ClientBuilder
          .standard()
          .withCredentials(new AWSStaticCredentialsProvider(credentials))
          .withRegion(Regions.AP_SOUTH_1)
          .build();
        
        AWSS3ServiceImpl awsService = new AWSS3ServiceImpl(s3client);

        bucketName = "katarakonda-bucket";

        /*//creating a bucket
        if(awsService.doesBucketExist(bucketName)) {
            System.out.println("Bucket name is not available."
              + " Try again with a different Bucket name.");
            return;
        }
        awsService.createBucket(bucketName);*/
        
        //list all the buckets
        int i=1;
        for(Bucket s : awsService.listBuckets() ) {
            System.out.println("Bucket "+(i++)+ "=> " +s.getName());
        }
        
       /* //deleting bucket
        awsService.deleteBucket("bucket-test2");
        */
        
        //uploading object
        awsService.putObject(
          bucketName, 
          "Document/hello.txt",
          new File("C:/Users/Ram/Desktop/hello3.txt")
        );
	}

}
