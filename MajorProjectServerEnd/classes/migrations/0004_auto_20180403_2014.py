# -*- coding: utf-8 -*-
# Generated by Django 1.9.4 on 2018-04-03 20:14
from __future__ import unicode_literals

import datetime
from django.db import migrations, models
from django.utils.timezone import utc


class Migration(migrations.Migration):

    dependencies = [
        ('classes', '0003_auto_20180403_2011'),
    ]

    operations = [
        migrations.AlterField(
            model_name='classdata',
            name='title',
            field=models.CharField(default=datetime.datetime(2018, 4, 3, 20, 14, 49, 662110, tzinfo=utc), max_length=255),
            preserve_default=False,
        ),
    ]
