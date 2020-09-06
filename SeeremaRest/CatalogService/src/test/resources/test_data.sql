insert into country(name, region_name, postal_name, region_field, addr_formatter) 
	values('Canada', 'LL_PROVINCE', 'LL_POSTAL_CODE', 'short_name', 'simple_addr_fomatter');
insert into country(name, region_name, postal_name, region_field, addr_formatter) 
	values('USA', 'LL_STATE', 'LL_ZIP_CODE', 'short_name', 'simple_addr_fomatter');

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

insert into city(name, region_id) values('Toronto', 1);

insert into field_category(module_id, name, is_system) values(0, 'LL_COMPANY', 'Y');
insert into field_category(module_id, name, is_system) values(0, 'LL_PERSON', 'Y');

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

insert into binary_file(file_name, file_type, file_size, bfile_category_id)
	values('example.png', 'png', 100, 1);

insert into address(line_1, zip, city_id) values('Here we are', 'ABC123', 1);

insert into entity(module_id, field_cat_id, name) values(0, 1, 'Example');

insert into entity_field(entity_id, field_id, value) values(1,1, 'http://www.example.com/');
insert into entity_field(entity_id, field_id, value) values(1,2, '1-888-1234567');
insert into entity_field(entity_id, field_id, value) values(1,3, 'info@example.com');
insert into entity_field(entity_id, field_id, value) values(1,4, '1');
