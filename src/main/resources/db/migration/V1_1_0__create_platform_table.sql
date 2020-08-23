CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS platform
(
    id       UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name     TEXT NOT NULL
);

INSERT INTO platform (id, name)
VALUES
       ('7B3386BD-4CA0-4EF2-A384-8A7F30BB1238', 'App Sec Platform'),
       ('7BB9A2FC-2319-4C2A-A8FB-F480785CEC47', 'MDR Platform');

CREATE TABLE IF NOT EXISTS application
(
    id          UUID UNIQUE NOT NULL DEFAULT uuid_generate_v4(),
    name        TEXT        NOT NULL,
    description TEXT        NOT NULL,
    platform_id UUID        NOT NULL references platform (id) on delete cascade,
    PRIMARY KEY (id, platform_id) -- application has one platform id, ensure no duplicates
);

INSERT INTO application (id, name, description, platform_id)
VALUES ('9BFD3CC8-66C4-4217-94AC-2E43D3526D1A', 'DAST', 'Dynamic Application Security Testing',
        '7B3386BD-4CA0-4EF2-A384-8A7F30BB1238'),
       ('ABC8AE80-6CB2-4872-BC16-F68E9A1BAC77', 'SAST', 'Static Application Security Testing',
        '7B3386BD-4CA0-4EF2-A384-8A7F30BB1238'),
       ('CFF8DCC4-7C98-4BBD-83AA-34361A464DDB', 'RASP', 'Runtime Application Self Protection',
        '7B3386BD-4CA0-4EF2-A384-8A7F30BB1238'),
       ('01671422-E5B9-4A2C-8268-90EC15E3DB57', 'SIEM', 'Security Information and Event Management',
        '7BB9A2FC-2319-4C2A-A8FB-F480785CEC47'),
       ('E720C0BD-19A0-41D9-B23C-D831D9EF9E78', 'A', 'Z Test Data for Sort',
        '7BB9A2FC-2319-4C2A-A8FB-F480785CEC47'),
       ('427C9045-386E-4383-827F-0C3DE354DAC0', 'Z', 'A Test Data for Sort',
        '7BB9A2FC-2319-4C2A-A8FB-F480785CEC47');




CREATE TABLE IF NOT EXISTS organization
(
    id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    industry TEXT NOT NULL
);

INSERT INTO organization (id, name, industry)
VALUES
       ('4552B62A-2821-4902-82A2-C971C7F66C00', 'Ulster Bank', 'Finance'),
       ('75DBF226-209C-4A73-845E-F7A6FC816563', 'BT', 'Telecoms');

CREATE TABLE IF NOT EXISTS organization_application
(
    org_id UUID NOT NULL REFERENCES organization (id) on delete cascade,
    app_id UUID NOT NULL REFERENCES application (id) on delete cascade,
    PRIMARY KEY (org_id, app_id) -- this assumes an org cannot have more than one of the same application
);

INSERT INTO organization_application (org_id, app_id)
VALUES ('4552B62A-2821-4902-82A2-C971C7F66C00', '9BFD3CC8-66C4-4217-94AC-2E43D3526D1A'),
       ('4552B62A-2821-4902-82A2-C971C7F66C00', 'ABC8AE80-6CB2-4872-BC16-F68E9A1BAC77'),
       ('4552B62A-2821-4902-82A2-C971C7F66C00', 'CFF8DCC4-7C98-4BBD-83AA-34361A464DDB'),
       ('4552B62A-2821-4902-82A2-C971C7F66C00', 'E720C0BD-19A0-41D9-B23C-D831D9EF9E78'),
       ('4552B62A-2821-4902-82A2-C971C7F66C00', '427C9045-386E-4383-827F-0C3DE354DAC0'),

       ('75DBF226-209C-4A73-845E-F7A6FC816563', '9BFD3CC8-66C4-4217-94AC-2E43D3526D1A'),
       ('75DBF226-209C-4A73-845E-F7A6FC816563', '01671422-E5B9-4A2C-8268-90EC15E3DB57'),
       ('75DBF226-209C-4A73-845E-F7A6FC816563', 'E720C0BD-19A0-41D9-B23C-D831D9EF9E78'),
       ('75DBF226-209C-4A73-845E-F7A6FC816563', '427C9045-386E-4383-827F-0C3DE354DAC0');