insert into module(id, name) values(0, 'test');
insert into field_category(module_id, name, is_system) values(0, 'TEST_FIELD_CATEGORY', 'Y');
insert into field(name, field_category_id, is_system) values('TEST_FIELD', 1, 'Y');
insert into status(name, module_id, is_system) values('LL_NEW', 0, 'Y');
insert into status(name, module_id, is_system) values('LL_DONE', 0, 'Y');
insert into entity(module_id, field_cat_id, name) values(0, 1, 'Example');
insert into entity_ex(id, entity_id, status_id, user_name) values(1, 1, 1, 'anonymous');
insert into entity_field(entity_id, field_id, value) values(1,1, 'test_value');
insert into status_history (status_id, entity_id, user_name, created) values(1,1, 'anonymous', current_timestamp);