from django.shortcuts import render
from onboarding.models import KeysData, WelcomeData

# Create your views here.
def splash_screen(request):
    print("================== Inside splash screen =====================")

    response_json = {}
    print("\nVersion = " + str(version) + "\n")
    try:
        if request.method == 'GET':
            try:
                get_android_version = int(KeysData.objects.get(key='version').value)
                compulsory_update = KeysData.objects.get(key='compulsory_update').value
                response_json['version'] = get_android_version
                if int(compulsory_update) == 1:
                    response_json['compulsory_update'] = True
                if int(compulsory_update) == 0:
                    response_json['compulsory_update'] = False
                response_json['success'] = True
            except Exception as e:
                print("Exception Error", str(e))
                response_json['success'] = False
                response_json['message'] = "Something went Wrong" + str(e)
        else:
            response_json['success'] = False
            response_json['message'] = "Illegal Request"
        print("================== Close splash screen =====================")
        print(response_json)
        return JsonResponse(response_json)
    except Exception as e:
        print (str(e))
        response_json['success'] = False
        response_json['message'] = "Some error occured" + str(e)
    print(response_json)
    return JsonResponse(response_json)

def welcome(request):
    """
        This Welcome method is used in Old App
        which is used only by indian users
    """
    response_json = {}
    if request.method == 'GET':
        for x, y in request.GET.items():
            print("key,value ", x, ":", y)
        slider_list = []
        try:
            for o in WelcomeData.objects.all():
                welcome_details = {
                    'id': int(o.id),
                    'image_url': request.scheme + '://' + request.get_host() + "/media/" + str(o.image),
                    'message': str(o.quote)
                }
                slider_list.append(welcome_details)
            response_json[keys.KEY_RESPONSE_SUCCESS] = True
            response_json[keys.KEY_RESPONSE_MESSAGE] = 'Success'
            response_json['welcome_page'] = slider_list
        except Exception as e:
            print(e)
            response_json['success'] = False
            response_json['message'] = str(e)

    else:
        response_json['success'] = False
        response_json['message'] = "not get method"
    print(response_json)
    return JsonResponse(response_json)