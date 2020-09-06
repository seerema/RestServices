insert into module(id, name) values(1, 'task');

insert into status(module_id, name, is_system) values(1, 'LL_NEW_F', 'Y');
insert into status(module_id, name, is_system) values(1, 'LL_STARTED', 'Y');
insert into status(module_id, name, is_system) values(1, 'LL_ON_HOLD', 'Y');
insert into status(module_id, name, is_system) values(1, 'LL_COMPLETED', 'Y');

insert into field_category(module_id, name, is_system) values(1, 'LL_REGULAR_TASK', 'Y');

insert into field(name, field_category_id, is_system) values('LL_NOTES', 1, 'Y');
insert into field(name, field_category_id, is_system) values('LL_TEST', 1, 'Y');

-- Temp task
insert into entity(module_id, name, field_cat_id) values(1, 'TO-DO', 1);
insert into entity_ex(id, entity_id, status_id, user_name) values(1, 1, 1, 'anonymous');
insert into entity_field(entity_id, field_id, value) values(1, 1, 'Sample TO_DO task');
insert into status_history (status_id, entity_id, user_name, created) values(1, 1, 'anonymous', current_timestamp);
