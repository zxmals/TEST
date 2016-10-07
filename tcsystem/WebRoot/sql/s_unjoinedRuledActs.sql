SELECT VACollectiveAct.ActID,VACollectiveAct.ActName,VACollectiveActivitiesPublish.ActDate,
VACollectiveAct.Attendee,VAUnJoinRecord.unjoinreason,VAUnJoinRecord.leavereqobtain,
VAUnJoinRecord.resultscore,VAUnJoinRecord.Asparetire
FROM (VACollectiveAct INNER JOIN VACollectiveActivitiesPublish
ON VACollectiveAct.ActType = '1'/*制定活动类型为规定性集体活动*/
/*活动一经发布，并且所有人都参与了，即使活动本身被取消，绩效还算，仍然可以通过添加该活动的参与记录获得相应的绩效分*/
AND VACollectiveAct.ActID = VACollectiveActivitiesPublish.ActID
AND VACollectiveActivitiesPublish.SpareTire = '1'
AND VACollectiveActivitiesPublish.ActDate BETWEEN '2012-03-01' AND '2017-08-01'
AND VACollectiveActivitiesPublish.ActPubID IN(
	SELECT VACollectiveActivitiesPublish.ActPubID
	FROM VACollectiveActivitiesPublish
	WHERE ActPubID NOT IN(
		SELECT VACollectiveActivitiesPublish.ActPubID
		FROM VATeacherAndCollectiveAct,VACollectiveActivitiesPublish
		WHERE VATeacherAndCollectiveAct.TeacherID = '091300422'
		AND VACollectiveActivitiesPublish.ActPubID = VATeacherAndCollectiveAct.ActPubID)
))LEFT JOIN VAUnJoinRecord 
ON VACollectiveAct.ActID = VAUnJoinRecord.ActID
AND VAUnJoinRecord.Sparetire = '1'