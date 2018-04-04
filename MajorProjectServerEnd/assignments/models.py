from __future__ import unicode_literals

from django.db import models
from classes.models import ClassData
from datetime import date
from users.models import UserData


# Create your models here.
class AssignmentData(models.Model):
	title = models.CharField(max_length=255,blank=True, null=True)
	description = models.CharField(max_length=255, blank=True)
	due_date = models.DateField(default=date.today)
	time_limit = models.IntegerField(default=0) # This field specifies the Time limit in seconds
	class_instance = models.ForeignKey(ClassData)
	input_data_file = models.FileField(upload_to='stdin/')
	output_data_file = models.FileField(upload_to='stdout/')
	created = models.DateTimeField(auto_now=False, auto_now_add=True)
	modified = models.DateTimeField(auto_now=True, auto_now_add=False)


	def __unicode__(self):
		return self.title

class AssignmentSubmissionData(models.Model):
	assignment_instance = models.ForeignKey(AssignmentData)
	user_instance = models.ForeignKey(UserData)
	submitted_code = models.FileField(upload_to='submission/')
	time_taken = models.IntegerField(default=0)
	response = models.TextField()
	created = models.DateTimeField(auto_now=False, auto_now_add=True)
	modified = models.DateTimeField(auto_now=True, auto_now_add=False)

