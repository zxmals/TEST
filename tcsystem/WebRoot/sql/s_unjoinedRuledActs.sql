SELECT VACollectiveAct.ActID,VACollectiveAct.ActName,VACollectiveActivitiesPublish.ActDate,VACollectiveAct.Attendee
FROM VACollectiveAct,VACollectiveActivitiesPublish
WHERE VACollectiveAct.ActType = '1'
AND VACollectiveAct.ActID = VACollectiveActivitiesPublish.ActID
AND VACollectiveActivitiesPublish.SpareTire = '1'
AND VACollectiveAct.SpareTire = '1'
AND VACollectiveAct.ActID NOT IN(
	SELECT VAUnJoinRecord.ActID FROM VAUnJoinRecord
	WHERE VAUnJoinRecord.Sparetire = '1'
	AND TeacherID = '091300422'
)
AND VACollectiveActivitiesPublish.ActPubID IN(
	SELECT VACollectiveActivitiesPublish.ActPubID
	FROM VACollectiveActivitiesPublish
	WHERE ActPubID NOT IN(
		SELECT VACollectiveActivitiesPublish.ActPubID
		FROM VATeacherAndCollectiveAct,VACollectiveActivitiesPublish
		WHERE VATeacherAndCollectiveAct.TeacherID = '091300422'
		AND VACollectiveActivitiesPublish.ActPubID = VATeacherAndCollectiveAct.ActPubID)
)
