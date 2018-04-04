from __future__ import unicode_literals

from django.db import models
from users.models import UserData

# Create your models here.
class ProfessorData(models.Model):
	name = models.CharField(max_length=255,blank=True, null=True)
	email = models.CharField(max_length=255, blank=True)
	password = models.CharField(max_length=255, blank=True, null=True)
	created = models.DateTimeField(auto_now=False, auto_now_add=True)
	modified = models.DateTimeField(auto_now=True, auto_now_add=False)

	def __unicode__(self):
		return self.name


class ClassData(models.Model):
	title = models.CharField(max_length=255,blank=False, null=False)
	description = models.TextField(max_length=255,blank=True,null=True)
	class_code = models.CharField(max_length=255,unique=True,blank=False,null=False) # This is the unique class code that will be used by users to join the class
	created = models.DateTimeField(auto_now=False, auto_now_add=True)
	modified = models.DateTimeField(auto_now=True, auto_now_add=False)

	def __unicode__(self):
		return self.title

class UserClassData(models.Model):
	user_instance = models.ForeignKey(UserData)
	class_instance = models.ForeignKey(ClassData)
	created = models.DateTimeField(auto_now=False, auto_now_add=True)
	modified = models.DateTimeField(auto_now=True, auto_now_add=False)