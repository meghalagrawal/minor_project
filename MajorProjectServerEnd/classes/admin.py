from django.contrib import admin
from classes.models import ClassData, ProfessorData, UserClassData

# Register your models here.
class ProfessorDataAdmin(admin.ModelAdmin):
    list_display = ["name","email"]
    search_fields = ["email"]

admin.site.register(ProfessorData, ProfessorDataAdmin)


class ClassDataAdmin(admin.ModelAdmin):
    list_display = ["title","description","created","modified"]
    search_fields = ["title"]

admin.site.register(ClassData, ClassDataAdmin)

class UserClassDataAdmin(admin.ModelAdmin):
    list_display = ["user_instance","class_instance","created","modified"]
    # search_fields = ["name"]


admin.site.register(UserClassData, UserClassDataAdmin)