# -- 코드를 입력하세요
# select 
# mp.member_name, 
# rr.review_text, date_format(rr.review_date,'%Y-%m-%d') as review_date 
# from 
# rest_review rr 
# left outer join 
# member_profile mp
# on rr.member_id = mp.member_id
# where mp.member_id = (
#     select member_id from rest_review 
#     group by member_id
#     order by count(*) desc
#     limit 1
# )
# order by review_date, review_text;


-- 코드를 입력하세요
with sum_review ( member_id, review_count
) as (
    select member_id, count(*) as review_count from rest_review
    group by member_id
), best_members (member_id
) as (
    select member_id
    from sum_review
    where review_count = (select max(review_count) from sum_review)
)
select 
mp.member_name, 
rr.review_text, date_format(rr.review_date,'%Y-%m-%d') as review_date 
from 
rest_review rr 
left outer join 
member_profile mp
on rr.member_id = mp.member_id
where mp.member_id in (select member_id from best_members)
order by review_date, review_text;