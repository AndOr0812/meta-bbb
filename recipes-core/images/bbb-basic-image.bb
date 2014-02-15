# Base this image on rpi-hwup-image
include bbb-hwup-image.bb

IMAGE_FEATURES += "ssh-server-dropbear splash"
IMAGE_INSTALL += " \
        avahi \
        ntp \ 
	"
