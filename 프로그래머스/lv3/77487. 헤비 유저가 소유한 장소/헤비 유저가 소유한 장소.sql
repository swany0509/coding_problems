-- 코드를 입력하세요
SELECT * FROM PLACES
WHERE HOST_ID IN (
    SELECT A.HOST_ID
    FROM (
        SELECT HOST_ID, COUNT(HOST_ID) AS CNT
        FROM PLACES
        GROUP BY HOST_ID
    ) A
    WHERE A.CNT >= 2
)
ORDER BY ID;