-- 코드를 입력하세요
with write_count (writer_id, articles) as (
    select writer_id, count(*) as articles
    from used_goods_board
    group by writer_id
), three_overs (writer_id) as (
    select writer_id
    from write_count
    where articles >= 3
)
select user_id, nickname, 
concat(city," ",street_address1," ", street_address2) as '전체주소',
concat(substr(tlno,1,3),'-',substr(tlno,4,4),'-',substr(tlno,8,4)) as '전화번호'
from used_goods_user ugu
where user_id in (select writer_id from three_overs)
order by user_id desc;