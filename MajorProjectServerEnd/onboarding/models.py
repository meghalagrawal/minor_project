from __future__ import unicode_literals

from django.db import models

# Create your models here.

class KeysData(models.Model):
    key = models.CharField(max_length=120, blank=True, null=True)
    value = models.CharField(max_length=200, blank=True, null=True)
    modified = models.DateTimeField(auto_now=True, auto_now_add=False)
    created = models.DateTimeField(auto_now=False, auto_now_add=True)


class WelcomeData(models.Model):
    image = models.ImageField(upload_to='welcome/', default='media/welcome/welcome.jpg')
    quote = models.TextField(max_length=300, blank=True, null=True)
    modified = models.DateTimeField(auto_now=True, auto_now_add=False)
    created = models.DateTimeField(auto_now=False, auto_now_add=True)