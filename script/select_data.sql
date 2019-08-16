SELECT * FROM kpi_report_temp a WHERE 
EXISTS (SELECT * FROM kpi_report_temp b
WHERE TO_DATE(b.create_date,'DD/MM/YYYY') BETWEEN '2019-01-01' AND '2019-04-30'
AND a.id=b.id) 
ORDER BY a.stt ASC, a.maso ASC, a.order_by ASC