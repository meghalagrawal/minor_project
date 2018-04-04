from __future__ import unicode_literals

from django.db import models

# Create your models here.
class UserData(models.Model):
	name = models.CharField(max_length=255,blank=True, null=True)
	email = models.CharField(unique=True,max_length=255, blank=True)
	password = models.CharField(max_length=255, blank=True, null=True)
	created = models.DateTimeField(auto_now=False, auto_now_add=True)
	modified = models.DateTimeField(auto_now=True, auto_now_add=False)

	def __unicode__(self):
		return self.name