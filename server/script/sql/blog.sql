create table blog_post
(
    id               bigint              not null
        primary key,
    title            varchar(64)         not null comment '标题',
    content_short    varchar(512)        not null comment '简介',
    content          text                not null comment '内容',
    author_signature varchar(64)         not null comment '作者署名',
    level_flag       char(1) default '0' not null comment '权限级别（0代表公开，1代表订阅权限，2代表仅自己可见）',
    del_flag         char(1) default '0' not null comment '删除标志（0代表存在 2代表删除）',
    create_by        bigint              not null comment '创建者',
    create_time      datetime            not null comment '创建时间',
    update_by        bigint              not null comment '更新者',
    update_time      datetime            not null comment '更新时间'
) comment '文章';

create table blog_comment
(
    id          bigint              not null
        primary key,
    reply_id    bigint              not null comment '回复id（0代表1级评论）',
    level       int                 not null comment '评论级别',
    post_id     bigint              not null comment '文章id',
    content     text                not null comment '评论内容',
    master_flag char(1) default '0' not null comment '主评论标志（0代表不是 1代表是）',
    del_flag    char(1) default '0' not null comment '删除标志（0代表存在 2代表删除）',
    create_by   bigint              not null comment '创建者',
    create_time datetime            not null comment '创建时间',
    update_by   bigint              not null comment '更新者',
    update_time datetime            not null comment '更新时间'
) comment '评论';

create table blog_tag
(
    id          bigint              not null
        primary key,
    name        varchar(64)         not null comment '标签名',
    del_flag    char(1) default '0' not null comment '删除标志（0代表存在 2代表删除）',
    create_by   bigint              not null comment '创建者',
    create_time datetime            not null comment '创建时间'
) comment '标签';

create table blog_post_tag
(
    post_id bigint not null comment '文章id',
    tag_id  bigint not null comment '标签id',
    primary key (post_id, tag_id)
) comment '文章标签关联表';

create table blog_collection
(
    id          bigint              not null
        primary key,
    name        varchar(64)         not null comment '合集名称',
    `describe`  varchar(512)        not null comment '合集描述',
    del_flag    char(1) default '0' not null comment '删除标志（0代表存在 2代表删除）',
    create_by   bigint              not null comment '创建者',
    create_time datetime            not null comment '创建时间',
    update_by   bigint              not null comment '更新者',
    update_time datetime            not null comment '更新时间'
) comment '合集';

create table blog_collection_post
(
    collection_id bigint not null comment '合集id',
    post_id       bigint not null comment '文章id',
    primary key (collection_id, post_id)
) comment '合集文章关联表';

create table blog_user_relationship
(
    user_id            bigint     not null comment '用户id',
    associated_user_id bigint     not null comment '关联用户',
    relationship_type  varchar(1) not null comment '关系类型（1代表关注 2代表订阅 3代表拉黑）',
    primary key (user_id, associated_user_id, relationship_type)
) comment '用户关系表';

create table blog_private_chat
(
    id                  bigint not null
        primary key,
    user_a_id           bigint not null comment '用户Aid',
    user_b_id           bigint not null comment '用户Bid',
    user_a_unread_count int    not null comment '用户A未读消息数',
    user_b_unread_count int    not null comment '用户B未读消息数'
) comment '私聊';

create table blog_private_chat_message
(
    id          bigint              not null
        primary key,
    chat_id     bigint              not null comment '私聊id',
    sender_id   bigint              not null comment '发送者id',
    receiver_id bigint              not null comment '接收者id',
    content     text                not null comment '消息内容',
    read_flag   char(1) default '0' not null comment '已读标志（0代表未读 1代表已读）',
    del_flag    char(1) default '0' not null comment '删除标志（0代表存在 2代表删除）',
    create_by   bigint              not null comment '创建者',
    create_time datetime            not null comment '创建时间',
    update_by   bigint              not null comment '更新者',
    update_time datetime            not null comment '更新时间'
) comment '私聊消息';

create table blog_post_notice
(
    id          bigint              not null
        primary key,
    post_id     bigint              not null comment '文章id',
    notice_type varchar(1)          not null comment '通知类型（1代表点赞 2代表收藏 3代表评论 4代表回复）',
    notice_id   bigint              not null comment '通知id（点赞id、收藏id、评论id、回复id）',
    read_flag   char(1) default '0' not null comment '已读标志（0代表未读 1代表已读）',
    del_flag    char(1) default '0' not null comment '删除标志（0代表存在 2代表删除）',
    create_by   bigint              not null comment '创建者',
    create_time datetime            not null comment '创建时间',
    update_by   bigint              not null comment '更新者',
    update_time datetime            not null comment '更新时间'
) comment '文章通知';

create table blog_system_notification
(
    id          bigint              not null
        primary key,
    title       varchar(64)         not null comment '标题',
    content     text                not null comment '内容',
    click       char(1)             not null comment '可点击标志（0代表未读 1代表可点击）',
    del_flag    char(1) default '0' not null comment '删除标志（0代表存在 2代表删除）',
    create_by   bigint              not null comment '创建者',
    create_time datetime            not null comment '创建时间',
    update_by   bigint              not null comment '更新者',
    update_time datetime            not null comment '更新时间'
) comment '系统通知';

create table blog_system_notification_read
(
    system_notification_id bigint not null comment '系统通知id',
    user_id                bigint not null comment '用户id',
    primary key (system_notification_id, user_id)
) comment '系统通知查看表';
