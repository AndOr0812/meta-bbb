About
=====

meta-bbb is a BSP layer to build yocto/poky images for Beaglebone
Black boards. It only supports the Beaglebone black, neither
Beaglebone white nor Beagleboards are supported. It was born from the
need to generate a minimal network enabled system to use as a basis
for custom deployments, as I was unable to generate a working system
with the official meta-ti and meta-beagleboard layers. (According to 
google I was not alone with my problems ;-)

This layer is a implementation of Robert Nelsons tutorial at 
http://eewiki.net/display/linuxonarm/BeagleBone+Black


Features
========

Kernel 3.13.1
u-boot 2013.10 with dedicated beaglebone black support
beaglebone-black device tree
sysvinit based
works with any yocot/poky image recipe
network enabled, dropbear ssh, avahi announcements
SD-card image ready to dd onto a SD-card


Known issues
============

No HDMI graphics support (neither at bootup nor later). Given the
picky nature of the BBB HDMI Interface (see
http://elinux.org/Beagleboard:BeagleBoneBlack_HDMI) and the fact that
even with the official binary distribution it was more of an
hit-or-miss I didn't bother. You'll need to pick up a serial cable to
follow the bot process. Or just count to 30, ssh root@bbb.local and
hope for the best ;-)

(Probably) no cape support





Dependencies
============

This layer depends on:

  URI: git://git.openembedded.org/bitbake
  branch: master

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: master

  URI: git://git.yoctoproject.org/meta
  branch: master


Patches
=======

Please submit any patches against this BSP to the Yocto mailing list
(yocto@yoctoproject.org) and cc: the maintainer:

Maintainer: Larissa Naber <larissa.naber@gmail.com>



Table of Contents
=================

  I. Building the meta-bbb BSP layer
 II. Booting the images from separate download


I. Building the meta-bbb BSP layer
========================================

Clone the repository to the top-level of your yocto build tree (or any
other place, provided you know to configure that setup correctly,-),
you can build a bbb image by adding the location of the meta-bbb layer
to bblayers.conf, along with any other layers needed (to access common
metadata shared between BSPs) e.g.:

  yocto/meta-bbb \

To enable the bbb layer, add the bbb MACHINE to local.conf:

  MACHINE ?= "bbb"

You should then be able to build a bbb image as such:

  $ source oe-init-build-env
  $ bitbake bbb-basic-image

or you can build various standard yocto images such as:

core-image-basic
core-image-lsb
core-image-minimal
core-image-minimal-dev

At the end of a successful build, you should have a several files in
side build/tmp/deploy/images/bbb

zImage                            kernel 3.13.1
zImage-am335x-boneblack.dtb       devicetree matching the kernel
u-boot.bin                        u-boot bootloader
MLO                               primary boot loader
uEnv.txt                          u-boot configuration file
core-image-lsb-bbb.tar.gz         rootfs tarball
core-image-lsb-bbb.ext3           root file system (mountable via loopback)

and a SD-card image named something like [IMAGENAME]-[TIMESTAMP].bbb-sdimg
e.g. core-image-lsb-bbb-20140215103519.bbb-sdimg

The SD-card image can be directly written to an sd card using dd:
sudo dd if=core-image-lsb-bbb-20140215103519.bbb-sdimg of=/dev/sde

CAUTION! Replace /dev/sde with the appropriate path to your sd card!

If you find you're getting corrupt images on the USB (it doesn't show
the syslinux boot: prompt, or the boot: prompt contains strange
characters), try doing this first:

# dd if=/dev/zero of=/dev/sdf bs=1M count=512

Insert the sd card into your Beaglebone Black and power up the
system. To follow the boot messages, you will need to connect to your
BBB via serial cable and follow the output via terminal. Once powered
up, your BBB will request an ip adress via dhcp and announce a ssh
service bbb.local via avahi/bonjour/zeroconf.  Use ssh root@bbb.local
to connect to your BBB. The root password is not required (you might
want to change that ;-)
 


