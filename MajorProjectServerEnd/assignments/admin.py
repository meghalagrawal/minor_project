from django.contrib import admin
from assignments.models import AssignmentData, AssignmentSubmissionData

# Register your models here.
class AssignmentDataAdmin(admin.ModelAdmin):
    list_display = ["title","due_date"]
    search_fields = ["title"]


admin.site.register(AssignmentData, AssignmentDataAdmin)

class AssignmentSubmissionDataAdmin(admin.ModelAdmin):
    list_display = ["assignment_instance","user_instance","time_taken"]
    # search_fields = ["title"]


admin.site.register(AssignmentSubmissionData, AssignmentSubmissionDataAdmin)