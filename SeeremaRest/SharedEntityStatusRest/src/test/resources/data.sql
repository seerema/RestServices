insert into module(id, name) values(0, 'test');
insert into user(name) values('anonymous');
insert into user(name) values('user');
insert into field_category(module_id, name, is_system) values(0, 'TEST_FIELD_CATEGORY', 'Y');
-- Add non-system field category
insert into field_category(module_id, name) values(0, 'TEST_NON_SYSTEM');
insert into field(name, field_category_id, is_system) values('TEST_FIELD', 1, 'Y');
insert into status(name, module_id, is_system) values('LL_NEW', 0, 'Y');
insert into status(name, module_id, is_system) values('LL_DONE', 0, 'Y');
insert into entity(module_id, field_cat_id, name) values(0, 1, 'Example');
insert into entity_ex(id, entity_id, status_id, user_id) values(1, 1, 1, 1);
insert into entity_field(entity_id, field_id, value) values(1,1, 'test_value');
insert into entity_status_history(status_id, entity_id, user_id, created) values(1, 1, 1, current_timestamp);
insert into entity_user_history(entity_id, owner_id, user_id, created) values(1, 1, 1, current_timestamp);
