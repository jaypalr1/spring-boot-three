# Table of Contents:

1. [Batch](#batch)
2. [ImageUploadDownload](#ImageUploadDownload)
3. [MultipartRequestAndRequestBody](#MultipartRequestAndRequestBody)
4. [ScriptExecutionFromJava](#ScriptExecutionFromJava)
5. [SendMail](#sendmail)
6. [VideoStream](#videostream)
7. [VideoUploadDownload](#videouploaddownload)

## Batch

[Official Doc](https://docs.spring.io/spring-batch/docs/current/reference/html/index.html)
https://hevodata.com/learn/batch-processing-in-spring-boot/

[Issue 1](https://stackoverflow.com/questions/55381505/preparedstatementcallback-bad-sql-grammar-select-job-instance-id-job-name-fro)
[Issue 2](https://stackoverflow.com/questions/47085330/prevent-spring-batch-automatic-job-trigger-after-context-creation-without-spring)
[Issue 3](https://stackoverflow.com/questions/48424367/allowing-core-thread-timeout-with-scheduledthreadpoolexecutor)

## ImageUploadDownload

## MultipartRequestAndRequestBody

## ScriptExecutionFromJava

## SendMail

## VideoStream

## VideoUploadDownload

2 MB limit

````
ALTER TABLE IMAGE_MODEL ALTER COLUMN PIC_BYTE VARCHAR(2097152);

Delete FROM IMAGE_MODEL; 
SELECT * FROM IMAGE_MODEL;

````

[Tutorial Link 1](https://github.com/janzyka/blobs-jpa) (Worked) <br>
[Tutorial Link 2](https://dzone.com/articles/upload-and-retrieve-filesimages-using-spring-boot)

## POST Request:

![POST Request](image-upload-download/src/main/resources/static/PostRequestScrrenshot.png)

## GET Request:

http://localhost:8080/image/download/image_name.format {Format should be specified else 404 will be
thrown}
