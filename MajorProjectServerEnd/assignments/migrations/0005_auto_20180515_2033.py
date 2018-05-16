# -*- coding: utf-8 -*-
# Generated by Django 1.9.4 on 2018-05-15 20:33
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('assignments', '0004_assignmenttestcasedata'),
    ]

    operations = [
        migrations.AddField(
            model_name='assignmentsubmissiondata',
            name='failed_cases',
            field=models.IntegerField(default=1),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='assignmentsubmissiondata',
            name='passed_cases',
            field=models.IntegerField(default=2),
            preserve_default=False,
        ),
    ]
