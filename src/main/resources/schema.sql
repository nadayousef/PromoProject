CREATE TABLE Segmentation (segmentation_id INT PRIMARY KEY, msisdn VARCHAR(25));
CREATE TABLE SegmentationOffers(segmentation_id INT , offer_id INT);
CREATE TABLE OFFER(offer_id INT , offer_info VARCHAR(255),capping INT,capping_duration INT);
CREATE TABLE History (msisdn VARCHAR(25) PRIMARY KEY, offer_ids VARCHAR(25),date DATE,end_date DATE,capping INT);