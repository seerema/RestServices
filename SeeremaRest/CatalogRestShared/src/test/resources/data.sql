insert into module(id, name) values(0, 'catalog');

insert into country(name, region_name, postal_name, region_field, addr_formatter) 
	values('Canada', 'LL_PROVINCE', 'LL_POSTAL_CODE', 'short_name', 'simple_addr_fomatter');
insert into country(name, region_name, postal_name, region_field, addr_formatter) 
	values('USA', 'LL_STATE', 'LL_ZIP_CODE', 'short_name', 'simple_addr_fomatter');

-- Canada Provinces
insert into region(name, short_name, country_id) values('Ontario', 'ON', 1);
insert into region(name, short_name, country_id) values('Quebec', 'QC', 1);
insert into region(name, short_name, country_id) values('Nova Scotia', 'NS', 1);
insert into region(name, short_name, country_id) values('New Brunswick', 'NB', 1);
insert into region(name, short_name, country_id) values('Manitoba', 'MB', 1);
insert into region(name, short_name, country_id) values('British Columbia', 'BC', 1);
insert into region(name, short_name, country_id) values('Prince Edward Island', 'PE', 1);
insert into region(name, short_name, country_id) values('Saskatchewan', 'SK', 1);
insert into region(name, short_name, country_id) values('Alberta', 'AB', 1);
insert into region(name, short_name, country_id) values('Newfoundland and Labrador', 'NL', 1);
insert into region(name, short_name, country_id) values('Northwest Territories', 'NT', 1);
insert into region(name, short_name, country_id) values('Yukon', 'YT', 1);
insert into region(name, short_name, country_id) values('Nunavut', 'NU', 1);

-- US States
insert into region(name, short_name, country_id) values('Alabama', 'AL', 2);
insert into region(name, short_name, country_id) values('Alaska',  'AK', 2);
insert into region(name, short_name, country_id) values('Arizona', 'AZ', 2);
insert into region(name, short_name, country_id) values('Arkansas', 'AR', 2);
insert into region(name, short_name, country_id) values('California', 'CA', 2);
insert into region(name, short_name, country_id) values('Colorado', 'CO', 2);
insert into region(name, short_name, country_id) values('Connecticut', 'CT', 2);
insert into region(name, short_name, country_id) values('Delaware', 'DE', 2);
insert into region(name, short_name, country_id) values('Florida', 'FL', 2);
insert into region(name, short_name, country_id) values('Georgia', 'GA', 2);
insert into region(name, short_name, country_id) values('Hawaii', 'HI', 2);
insert into region(name, short_name, country_id) values('Idaho', 'ID', 2);
insert into region(name, short_name, country_id) values('Illinois', 'IL', 2);
insert into region(name, short_name, country_id) values('Indiana', 'IN', 2);
insert into region(name, short_name, country_id) values('Iowa', 'IA', 2);
insert into region(name, short_name, country_id) values('Kansas', 'KS', 2);
insert into region(name, short_name, country_id) values('Kentucky', 'KY', 2);
insert into region(name, short_name, country_id) values('Louisiana', 'LA', 2);
insert into region(name, short_name, country_id) values('Maine', 'ME', 2);
insert into region(name, short_name, country_id) values('Maryland', 'MD', 2);
insert into region(name, short_name, country_id) values('Massachusetts', 'MA', 2);
insert into region(name, short_name, country_id) values('Michigan', 'MI', 2);
insert into region(name, short_name, country_id) values('Minnesota', 'MN', 2);
insert into region(name, short_name, country_id) values('Mississippi', 'MS', 2);
insert into region(name, short_name, country_id) values('Missouri', 'MO', 2);
insert into region(name, short_name, country_id) values('Montana', 'MT', 2);
insert into region(name, short_name, country_id) values('Nebraska', 'NE', 2);
insert into region(name, short_name, country_id) values('Nevada', 'NV', 2);
insert into region(name, short_name, country_id) values('New Hampshire', 'NH', 2);
insert into region(name, short_name, country_id) values('New Jersey', 'NJ', 2);
insert into region(name, short_name, country_id) values('New Mexico', 'NM', 2);
insert into region(name, short_name, country_id) values('New York', 'NY', 2);
insert into region(name, short_name, country_id) values('North Carolina', 'NC', 2);
insert into region(name, short_name, country_id) values('North Dakota', 'ND', 2);
insert into region(name, short_name, country_id) values('Ohio', 'OH', 2);
insert into region(name, short_name, country_id) values('Oklahoma', 'OK', 2);
insert into region(name, short_name, country_id) values('Oregon', 'OR', 2);
insert into region(name, short_name, country_id) values('Pennsylvania', 'PA', 2);
insert into region(name, short_name, country_id) values('Rhode Island', 'RI', 2);
insert into region(name, short_name, country_id) values('South Carolina', 'SC', 2);
insert into region(name, short_name, country_id) values('South Dakota', 'SD', 2);
insert into region(name, short_name, country_id) values('Tennessee', 'TN', 2);
insert into region(name, short_name, country_id) values('Texas', 'TX', 2);
insert into region(name, short_name, country_id) values('Utah', 'UT', 2);
insert into region(name, short_name, country_id) values('Vermont', 'VT', 2);
insert into region(name, short_name, country_id) values('Virginia', 'VA', 2);
insert into region(name, short_name, country_id) values('Washington', 'WA', 2);
insert into region(name, short_name, country_id) values('West Virginia', 'WV', 2);
insert into region(name, short_name, country_id) values('Wisconsin', 'WI', 2);
insert into region(name, short_name, country_id) values('Wyoming', 'WY', 2);

insert into city(name, region_id) values('Toronto', 1);

insert into field_category(name, module_id, is_system) values('LL_COMPANY', 0, 'Y');
insert into field_category(name, module_id, is_system) values('LL_PERSON', 0, 'Y');

insert into field(name, field_category_id, is_system) values('LL_SITE', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_PHONE', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_EMAIL', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_ADDRESS', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_FAX', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_NOTES', 1, 'Y');

insert into field(name, field_category_id, is_system) values('LL_CELL_PHONE', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_HOME_PHONE', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_EMAIL', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_ADDRESS', 2, 'Y');
insert into field(name, field_category_id, is_system) values('LL_NOTES', 2, 'Y');

insert into bfile_category (name, is_system) values('LL_GENERAL', 'Y');
insert into bfile_category (name, is_system) values('LL_LOGO', 'Y');

insert into comm_media(name, is_system) values('LL_EMAIL', 'Y');
insert into comm_media(name, is_system) values('LL_PHONE_CALL', 'Y');
insert into comm_media(name, is_system) values('LL_SMS', 'Y');
insert into comm_media(name, is_system) values('LL_VIDEO_CHAT', 'Y');

-- Custom data
insert into address(line_1, zip, city_id) values('Here we are', 'ABC123', 1);

insert into entity(module_id, field_cat_id, name) values(0, 1, 'Example');

insert into entity_field(entity_id, field_id, value) values(1,1, 'http://www.example.com/');
insert into entity_field(entity_id, field_id, value) values(1,2, '1-888-1234567');
insert into entity_field(entity_id, field_id, value) values(1,3, 'info@example.com');
insert into entity_field(entity_id, field_id, value) values(1,4, '1');

